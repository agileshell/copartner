package com.insoul.copartner.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.Channel;
import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.constant.MessageType;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.constant.UserStatus;
import com.insoul.copartner.dao.IMessageDao;
import com.insoul.copartner.dao.IUserAccountConfirmationDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.IUserPasswordResetDao;
import com.insoul.copartner.domain.Message;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.domain.UserAccountConfirmation;
import com.insoul.copartner.domain.UserAccountConfirmationId;
import com.insoul.copartner.domain.UserPasswordReset;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IUserService;
import com.insoul.copartner.util.CodeUtil;
import com.insoul.copartner.util.MD5Encrypt;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.copartner.util.ValidationUtil;
import com.insoul.copartner.util.mail.MailUtil;
import com.insoul.copartner.util.sms.SMSUtil;
import com.insoul.copartner.vo.request.UserAddRequest;

@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserAccountConfirmationDao userAccountConfirmationDao;

    @Resource
    private IUserPasswordResetDao userPasswordResetDao;

    @Resource
    private IMessageDao messageDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public long register(final UserAddRequest userAddRequest) throws CException {
        String account = userAddRequest.getAccount();
        boolean isMobile = ValidationUtil.isMobilePhoneNumber(account);
        User user = new User();
        if (isMobile) {
            if (null != userDao.getUserByMobile(account)) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.MOBILE_REGISTERED);
            }

            String code = userAddRequest.getCode();
            UserAccountConfirmation userAccountConfirmation = userAccountConfirmationDao.getUserAccountConfirmation(
                    CommonConstant.MOBILE, account, code);
            if (null != userAccountConfirmation) {
                userAccountConfirmation.setIsConfirmed(true);
                userAccountConfirmation.setConfirmed(new Date());
            } else {
                throw CExceptionFactory.getException(CException.class, ResponseCode.CODE_INCORRECT);
            }

            user.setMobile(account);
            user.setIsMobileVerified(true);
        } else {
            if (null != userDao.getUserByEmail(account)) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.EMAIL_REGISTERED);
            }

            user.setEmail(account);
            user.setIsEmailVerified(false);
        }

        Date now = new Date();
        user.setStatus(UserStatus.ACTIVE.getValue());
        user.setName(account);
        user.setClientIp(getIp());
        user.setCreated(now);
        String salt = PasswordUtil.genSalt();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encodePassword(userAddRequest.getPassword(), salt));

        userDao.save(user);

        Long userId = user.getId();

        if (!isMobile) {
            String code = CodeUtil.genVerfyCode(GlobalProperties.EMAIL_CODE_LENGTH, true);

            UserAccountConfirmationId id = new UserAccountConfirmationId();
            id.setAccount(account);
            id.setCode(code);
            id.setAccountType(CommonConstant.MOBILE);
            UserAccountConfirmation userAccountConfirmation = new UserAccountConfirmation();
            userAccountConfirmation.setId(id);
            userAccountConfirmation.setUserId(userId);
            userAccountConfirmation.setCreated(now);
            userAccountConfirmationDao.save(userAccountConfirmation);
            MD5Encrypt.MD5Encode(code);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userName", account);
            String verifyEmailURL = new StringBuilder(GlobalProperties.VERIFY_EMAIL_URL).append("?account=")
                    .append(account).append("&code=").append(code).toString();
            params.put("verifyEmailURL", verifyEmailURL);

            sendMail(userId, MessageType.REGISTER.getValue(), account, params);
        }

        return userId;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void retrievePassword(String account) throws CException {
        boolean isMobile = ValidationUtil.isMobilePhoneNumber(account);
        User user = isMobile ? userDao.getUserByMobile(account) : userDao.getUserByEmail(account);
        boolean userExisted = null != user;
        if (!userExisted || UserStatus.DELETED.getValue().equals(user.getStatus())) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.ACCOUNT_NOT_EXIST);
        } else if (userExisted && !UserStatus.ACTIVE.getValue().equals(user.getStatus())) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.ACCOUNT_INACTIVE);
        }

        if (isMobile) {
            String code = CodeUtil.genVerfyCode(GlobalProperties.SMS_CODE_LENGTH, false);

            UserPasswordReset userPasswordReset = new UserPasswordReset();
            userPasswordReset.setUserId(user.getId());
            userPasswordReset.setCode(code);
            userPasswordReset.setCreated(new Date());
            userPasswordResetDao.save(userPasswordReset);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("code", code);

            sendMessage(user.getId(), MessageType.RETRIEVE_PWD.getValue(), account, params);
        } else {
            String code = CodeUtil.genVerfyCode(GlobalProperties.EMAIL_CODE_LENGTH, true);

            UserPasswordReset userPasswordReset = new UserPasswordReset();
            userPasswordReset.setUserId(user.getId());
            userPasswordReset.setCode(code);
            userPasswordReset.setCreated(new Date());
            userPasswordResetDao.save(userPasswordReset);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userName", account);
            String retrievePwdCheck = new StringBuilder(GlobalProperties.RESET_PASSWORD_URL).append("?account=")
                    .append(account).append("&code=").append(code).toString();
            params.put("retrievePwdCheck", retrievePwdCheck);

            sendMail(user.getId(), MessageType.RETRIEVE_PWD.getValue(), account, params);
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void resetPassword(String account, String code, String password) throws CException {
        boolean isMobile = ValidationUtil.isMobilePhoneNumber(account);
        User user = isMobile ? userDao.getUserByMobile(account) : userDao.getUserByEmail(account);
        boolean userExisted = null != user;
        if (!userExisted || UserStatus.DELETED.getValue().equals(user.getStatus())) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.ACCOUNT_NOT_EXIST);
        } else if (userExisted && !UserStatus.ACTIVE.getValue().equals(user.getStatus())) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.ACCOUNT_INACTIVE);
        }

        UserPasswordReset userPasswordReset = userPasswordResetDao.getUserPasswordReset(user.getId(), code);
        if (null != userPasswordReset) {
            userPasswordReset.setIsReset(true);
            userPasswordReset.setReset(new Date());
        } else {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CODE_INCORRECT);
        }

        String salt = PasswordUtil.genSalt();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encodePassword(password, salt));
        user.setUpdated(new Date());
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void changePassword(String oldPassword, String password) throws CException {
        User user = userDao.get(getUserId());

        String encodeOldPassword = PasswordUtil.encodePassword(oldPassword, user.getSalt());
        if (!encodeOldPassword.equals(user.getPassword())) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.OLD_PASSWORD_INCORRECT);
        }

        String salt = PasswordUtil.genSalt();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encodePassword(password, salt));
        user.setUpdated(new Date());
    }

    private void sendMessage(long userId, int messageTypeId, String to, Map<String, Object> params) throws CException {
        int messageDailyCount = messageDao.getMessageDailyCount(to, Channel.SMS.getValue());
        if (messageDailyCount > GlobalProperties.SMS_DAILY_LIMIT) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.SMS_BEYOND_DAILY_LIMIT);
        }

        String content = SMSUtil.getContent(messageTypeId + "", params);

        Boolean sendFlag = true;
        if (GlobalProperties.SMS_ENABLED) {
            sendFlag = SMSUtil.sendSMS(to, content);
        }

        if (sendFlag) {
            String subject = SMSUtil.getSubject(messageTypeId + "");

            Message message = new Message();
            message.setAccount(to);
            message.setMessageTypeId(messageTypeId);
            message.setChannelId(Channel.SMS.getValue());
            message.setUserId(userId);
            message.setSubject(subject);
            message.setContent(content);
            message.setCreated(new Date());
            message.setStatus("sent");
            messageDao.save(message);
        } else {
            throw CExceptionFactory.getException(CException.class, ResponseCode.SMS_SEND_FAILED);
        }
    }

    private void sendMail(long userId, int messageTypeId, String to, Map<String, Object> params) {
        String subject = MailUtil.getMailSubject(messageTypeId + "");
        String content = MailUtil.getMailContent(messageTypeId + "", params);

        Message message = new Message();
        message.setAccount(to);
        message.setMessageTypeId(messageTypeId);
        message.setChannelId(Channel.EMAIL.getValue());
        message.setUserId(userId);
        message.setSubject(subject);
        message.setContent(content);
        message.setCreated(new Date());
        messageDao.save(message);

        MailUtil.sendMail(to, subject, content);
    }

}
