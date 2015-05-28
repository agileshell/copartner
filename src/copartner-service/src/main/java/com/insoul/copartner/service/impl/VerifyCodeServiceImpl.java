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
import com.insoul.copartner.constant.VerifyCodeType;
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
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IVerifyCodeService;
import com.insoul.copartner.util.CodeUtil;
import com.insoul.copartner.util.sms.SMSUtil;

@Service
public class VerifyCodeServiceImpl extends BaseServiceImpl implements IVerifyCodeService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IMessageDao messageDao;

    @Resource
    private IUserAccountConfirmationDao userAccountConfirmationDao;

    @Resource
    private IUserPasswordResetDao userPasswordResetDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void sendVerifyCode(String mobile, String type) throws CException {
        User user = userDao.getUserByMobile(mobile);
        boolean userExisted = null != user;
        int messageTypeId = 0;
        if (type.equals(VerifyCodeType.REGISTER.getValue())) {
            if (userExisted) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.MOBILE_REGISTERED);
            }
            messageTypeId = MessageType.REGISTER.getValue();
        } else if (type.equals(VerifyCodeType.RETRIEVEPWD.getValue())) {
            if (!userExisted || UserStatus.DELETED.getValue().equals(user.getStatus())) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.ACCOUNT_NOT_EXIST);
            } else if (userExisted && !UserStatus.ACTIVE.getValue().equals(user.getStatus())) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.ACCOUNT_INACTIVE);
            }
            messageTypeId = MessageType.RETRIEVE_PWD.getValue();
        } else {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        int messageDailyCount = messageDao.getMessageDailyCount(mobile, Channel.SMS.getValue());
        if (messageDailyCount > GlobalProperties.SMS_DAILY_LIMIT) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.SMS_BEYOND_DAILY_LIMIT);
        }

        String code = CodeUtil.genVerfyCode(GlobalProperties.SMS_CODE_LENGTH, false);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        String content = SMSUtil.getContent(messageTypeId + "", params);
        Boolean sendFlag = true;
        if (GlobalProperties.SMS_ENABLED) {
            sendFlag = SMSUtil.sendSMS(mobile, content);
        }

        if (sendFlag) {
            Long userId = userExisted ? user.getId() : 0L;
            Date now = new Date();
            if (type.equals(VerifyCodeType.REGISTER.getValue())) {
                UserAccountConfirmationId id = new UserAccountConfirmationId();
                id.setAccount(mobile);
                id.setCode(code);
                id.setAccountType(CommonConstant.MOBILE);
                UserAccountConfirmation userAccountConfirmation = new UserAccountConfirmation();
                userAccountConfirmation.setId(id);
                userAccountConfirmation.setCreated(now);
                userAccountConfirmationDao.save(userAccountConfirmation);
            } else if (type.equals(VerifyCodeType.RETRIEVEPWD.getValue())) {
                UserPasswordReset userPasswordReset = new UserPasswordReset();
                userPasswordReset.setUserId(userId);
                userPasswordReset.setCode(code);
                userPasswordReset.setCreated(now);
                userPasswordResetDao.save(userPasswordReset);
            }

            String subject = SMSUtil.getSubject(messageTypeId + "");

            Message message = new Message();
            message.setAccount(mobile);
            message.setMessageTypeId(messageTypeId);
            message.setChannelId(Channel.SMS.getValue());
            message.setUserId(userId);
            message.setSubject(subject);
            message.setContent(content);
            message.setCreated(now);
            message.setStatus("sent");
            messageDao.save(message);
        } else {
            throw CExceptionFactory.getException(CException.class, ResponseCode.SMS_SEND_FAILED);
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void verifyCode(String mobile, String type, String code) throws CException {
        User user = userDao.getUserByMobile(mobile);
        boolean hasCode = false;
        if (type.equals(VerifyCodeType.REGISTER.getValue())) {
            UserAccountConfirmation userAccountConfirmation = userAccountConfirmationDao.getUserAccountConfirmation(
                    CommonConstant.MOBILE, mobile, code);
            if (null != userAccountConfirmation) {
                hasCode = true;
            }
        } else if (type.equals(VerifyCodeType.RETRIEVEPWD.getValue())) {
            if (null != user && UserStatus.ACTIVE.getValue().equals(user.getStatus())) {
                UserPasswordReset userPasswordReset = userPasswordResetDao.getUserPasswordReset(user.getId(), code);
                if (null != userPasswordReset) {
                    hasCode = true;
                }
            }
        } else {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        if (!hasCode) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CODE_INCORRECT);
        }
    }
}
