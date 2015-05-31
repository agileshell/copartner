package com.insoul.copartner.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.Channel;
import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.constant.MessageType;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.constant.UserStatus;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.ILocationDao;
import com.insoul.copartner.dao.IMessageDao;
import com.insoul.copartner.dao.IResumeDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.IStartupStatusDao;
import com.insoul.copartner.dao.IUserAccountConfirmationDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.IUserPasswordResetDao;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Location;
import com.insoul.copartner.domain.Message;
import com.insoul.copartner.domain.Resume;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.copartner.domain.StartupStatus;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.domain.UserAccountConfirmation;
import com.insoul.copartner.domain.UserAccountConfirmationId;
import com.insoul.copartner.domain.UserPasswordReset;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IUserService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.CodeUtil;
import com.insoul.copartner.util.MD5Encrypt;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.copartner.util.ValidationUtil;
import com.insoul.copartner.util.mail.MailUtil;
import com.insoul.copartner.util.sms.SMSUtil;
import com.insoul.copartner.vo.IndustryDomainVO;
import com.insoul.copartner.vo.LocationVO;
import com.insoul.copartner.vo.ResumeVO;
import com.insoul.copartner.vo.StartupRoleVO;
import com.insoul.copartner.vo.StartupStatusVO;
import com.insoul.copartner.vo.UserDetailVO;
import com.insoul.copartner.vo.request.ResumeRequest;
import com.insoul.copartner.vo.request.UserAddRequest;
import com.insoul.copartner.vo.request.UserProfileUpdateRequest;

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

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IStartupRoleDao startupRoleDao;

    @Resource
    private IStartupStatusDao startupStatusDao;

    @Resource
    private IIndustryDomainDao industryDomainDao;

    @Resource
    private IResumeDao resumeDao;

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
        user.setName(account);
        user.setClientIp(getIp());
        user.setCreated(now);
        String salt = PasswordUtil.genSalt();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encodePassword(userAddRequest.getPassword(), salt));

        setDefaultValue(user);

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

    private void setDefaultValue(User user) {
        user.setStatus(UserStatus.ACTIVE.getValue());

        List<StartupRole> roles = startupRoleDao.findAll();
        if (null != roles && roles.size() > 0) {
            user.setStartupRoleId(roles.get(0).getId());
        }

        List<StartupStatus> statuses = startupStatusDao.findAll();
        if (null != statuses && statuses.size() > 0) {
            user.setStartupStatusId(statuses.get(0).getId());
        }

        Long locationId = GlobalProperties.DEFAULT_LOCATION_ID.longValue();
        if (0 != locationId) {
            Location location = locationDao.get(locationId);
            if (null != location) {
                user.setLocationId(locationId);

                // 缓存地区全名
                StringBuilder fullLocation = new StringBuilder();
                fullLocation.append(location.getName());

                Location parentLocation = locationDao.get(location.getParentId());
                if (null != parentLocation) {
                    fullLocation.append("|").append(parentLocation.getName());
                }
                user.setFullLocation(fullLocation.toString());
            }
        }

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

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void updateProfile(UserProfileUpdateRequest profileUpdateRequest) throws CException {
        long userId = getUserId();
        User user = userDao.get(userId);

        if (StringUtils.isNotBlank(profileUpdateRequest.getName())) {
            user.setName(profileUpdateRequest.getName());
        }
        if (StringUtils.isNotBlank(profileUpdateRequest.getAvatar())) {
            user.setAvatar(profileUpdateRequest.getAvatar());
        }
        if (StringUtils.isNotBlank(profileUpdateRequest.getGender())) {
            user.setGender(profileUpdateRequest.getGender());
        }
        if (StringUtils.isNotBlank(profileUpdateRequest.getAge())) {
            user.setAge(profileUpdateRequest.getAge());
        }
        if (StringUtils.isNotBlank(profileUpdateRequest.getIntroduction())) {
            user.setIntroduction(profileUpdateRequest.getIntroduction());
        }

        if (null != profileUpdateRequest.getLocationId()) {
            Location location = locationDao.get(profileUpdateRequest.getLocationId());
            if (null == location) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.LOCATION_NOT_EXIST);
            }

            // 缓存地区全名
            StringBuilder fullLocation = new StringBuilder();
            fullLocation.append(location.getName());
            Location parentLocation = locationDao.get(location.getParentId());
            if (null != parentLocation) {
                fullLocation.append("|").append(parentLocation.getName());
            }
            user.setFullLocation(fullLocation.toString());

            user.setLocationId(profileUpdateRequest.getLocationId());
        }

        if (null != profileUpdateRequest.getStartupStatusId()) {
            StartupStatus startupStatus = startupStatusDao.get(profileUpdateRequest.getStartupStatusId());
            if (null == startupStatus) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.STARTUP_STATUS_NOT_EXIST);
            }

            user.setStartupStatusId(profileUpdateRequest.getStartupStatusId());
        }
        if (null != profileUpdateRequest.getStartupRoleId()) {
            StartupRole startupRole = startupRoleDao.get(profileUpdateRequest.getStartupRoleId());
            if (null == startupRole) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.STARTUP_ROLE_NOT_EXIST);
            }

            user.setStartupRoleId(profileUpdateRequest.getStartupRoleId());
        }

        Long domainIds[] = profileUpdateRequest.getDomainIds();
        if (null != domainIds && domainIds.length > 0) {
            StringBuilder strDomainIds = new StringBuilder();

            for (Long domainId : domainIds) {
                IndustryDomain industryDomain = industryDomainDao.get(domainId);
                if (null != industryDomain) {
                    strDomainIds.append(domainId).append(",");
                }
            }

            user.setDomains(strDomainIds.toString());
        }
    }

    @Override
    public UserDetailVO getUserProfileDetail() {
        User user = userDao.get(getUserId());

        return getUserProfile(user);
    }

    @Override
    public UserDetailVO getUserProfileDetail(Long userId) throws CException {
        User user = userDao.get(userId);
        if (null == user) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.USER_NOT_EXIST);
        }

        return getUserProfile(user);
    }

    private UserDetailVO getUserProfile(User user) {

        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setUserId(user.getId());
        userDetailVO.setName(user.getName());
        userDetailVO.setMobile(user.getMobile());
        userDetailVO.setEmail(user.getEmail());
        userDetailVO.setStatus(user.getStatus());
        userDetailVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
        userDetailVO.setGender(user.getGender());
        userDetailVO.setAge(user.getAge());
        userDetailVO.setIntroduction(user.getIntroduction());
        userDetailVO.setIsMobileVerified(user.getIsMobileVerified());
        userDetailVO.setIsEmailVerified(user.getIsEmailVerified());

        if (null != user.getLocationId()) {
            Location location = locationDao.get(user.getLocationId());
            if (null != location) {
                LocationVO locationVO = new LocationVO();
                locationVO.setLocationId(location.getId());
                locationVO.setParentId(location.getParentId());
                locationVO.setName(location.getName());
                locationVO.setPinyin(location.getPinyin());
                locationVO.setLatitude(location.getLatitude());
                locationVO.setLongitude(location.getLongitude());

                userDetailVO.setLocation(locationVO);

                userDetailVO.setFullLocation(user.getFullLocation());
            }
        }

        if (null != user.getStartupStatusId()) {
            StartupStatus startupStatus = startupStatusDao.get(user.getStartupStatusId());
            if (null != startupStatus) {
                StartupStatusVO startupStatusVO = new StartupStatusVO();
                startupStatusVO.setId(startupStatus.getId());
                startupStatusVO.setName(startupStatus.getName());

                userDetailVO.setStartupStatus(startupStatusVO);
            }
        }
        if (null != user.getStartupRoleId()) {
            StartupRole startupRole = startupRoleDao.get(user.getStartupRoleId());
            if (null != startupRole) {
                StartupRoleVO startupRoleVO = new StartupRoleVO();
                startupRoleVO.setId(startupRole.getId());
                startupRoleVO.setName(startupRole.getName());

                userDetailVO.setStartupRole(startupRoleVO);
            }
        }
        if (StringUtils.isNoneBlank(user.getDomains())) {
            Set<IndustryDomainVO> domains = new HashSet<IndustryDomainVO>();

            StringBuilder fullDomains = new StringBuilder();
            String domainIds[] = user.getDomains().split(",");
            for (String domainId : domainIds) {
                if (StringUtils.isNumeric(domainId)) {
                    IndustryDomain industryDomain = industryDomainDao.get(Long.valueOf(domainId));
                    if (null != industryDomain) {
                        IndustryDomainVO industryDomainVO = new IndustryDomainVO();
                        industryDomainVO.setId(industryDomain.getId());
                        industryDomainVO.setName(industryDomain.getName());

                        domains.add(industryDomainVO);
                        fullDomains.append(industryDomain.getName()).append("  ");
                    }
                }
            }

            userDetailVO.setDomains(domains);
            userDetailVO.setFullDomains(fullDomains.toString().trim());
        }

        List<Resume> educationResumes = resumeDao.getByUserIdAndType(user.getId(), (byte) 1);
        userDetailVO.setEducationResumes(formatResumes(educationResumes));

        List<Resume> workResumes = resumeDao.getByUserIdAndType(user.getId(), (byte) 2);
        userDetailVO.setWorkResumes(formatResumes(workResumes));

        return userDetailVO;
    }

    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    @Override
    public void updateEducationResume(List<ResumeRequest> requestDatas) {
        updateResume(requestDatas, (byte) 1);
    }

    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    @Override
    public void updateWorkResume(List<ResumeRequest> requestDatas) {
        updateResume(requestDatas, (byte) 2);
    }

    public void updateResume(List<ResumeRequest> requestDatas, Byte type) {
        Long currentUserId = getUserId();

        resumeDao.deleteByUserIdAndType(currentUserId, type);

        Date now = new Date();
        for (ResumeRequest resumeRequest : requestDatas) {
            Resume resume = new Resume();
            resume.setUserId(currentUserId);
            resume.setName(resumeRequest.getName());
            resume.setMajor(resumeRequest.getMajor());
            resume.setType(type);
            resume.setCreated(now);

            resumeDao.save(resume);
        }
    }

    @Override
    public Set<ResumeVO> listUserEducationResume() {
        List<Resume> resumes = resumeDao.getByUserIdAndType(getUserId(), (byte) 1);

        return formatResumes(resumes);
    }

    @Override
    public Set<ResumeVO> listUserWorkResume() {
        List<Resume> resumes = resumeDao.getByUserIdAndType(getUserId(), (byte) 2);

        return formatResumes(resumes);
    }

    private Set<ResumeVO> formatResumes(List<Resume> resumes) {
        Set<ResumeVO> resumeVOs = new HashSet<ResumeVO>();
        for (Resume resume : resumes) {
            ResumeVO vo = new ResumeVO();
            vo.setName(resume.getName());
            vo.setMajor(resume.getMajor());

            resumeVOs.add(vo);
        }

        return resumeVOs;
    }
}
