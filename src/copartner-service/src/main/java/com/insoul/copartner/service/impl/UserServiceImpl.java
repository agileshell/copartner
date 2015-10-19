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
import com.insoul.copartner.dao.IUser3rdAccountAccessDao;
import com.insoul.copartner.dao.IUserAccountConfirmationDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.IUserFriendsDao;
import com.insoul.copartner.dao.IUserPasswordResetDao;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Location;
import com.insoul.copartner.domain.Message;
import com.insoul.copartner.domain.Resume;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.copartner.domain.StartupStatus;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.domain.User3rdAccountAccess;
import com.insoul.copartner.domain.UserAccountConfirmation;
import com.insoul.copartner.domain.UserAccountConfirmationId;
import com.insoul.copartner.domain.UserFriends;
import com.insoul.copartner.domain.UserFriendsId;
import com.insoul.copartner.domain.UserPasswordReset;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.im.IMUtils;
import com.insoul.copartner.service.IUserService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.CodeUtil;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.copartner.util.ValidationUtil;
import com.insoul.copartner.util.mail.MailUtil;
import com.insoul.copartner.util.sms.SMSUtil;
import com.insoul.copartner.vo.IndustryDomainVO;
import com.insoul.copartner.vo.LoginResponse;
import com.insoul.copartner.vo.ResumeVO;
import com.insoul.copartner.vo.StartupRoleVO;
import com.insoul.copartner.vo.StartupStatusVO;
import com.insoul.copartner.vo.UserDetailVO;
import com.insoul.copartner.vo.request.ResumeRequest;
import com.insoul.copartner.vo.request.SignInByThirdPartRequest;
import com.insoul.copartner.vo.request.UserAddRequest;
import com.insoul.copartner.vo.request.UserAuthenticateRequest;
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

    @Resource
    private IUserFriendsDao userFriendsDAO;

    @Resource
    private IUser3rdAccountAccessDao user3rdAccountAccessDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void userAuthenticate(UserAuthenticateRequest request) throws CException {
        long userId = getUserId();
        User user = userDao.get(userId);
        user.setRoleId(request.getRoleId());
        if (StringUtils.isNotBlank(request.getAuthenticationInfo())) {
            user.setAuthenticationInfo(request.getAuthenticationInfo());
        }
        if (StringUtils.isNotBlank(request.getIdNumber())) {
            user.setIdNumber(request.getIdNumber());
        }
        if (StringUtils.isNotBlank(request.getIdPicture())) {
            user.setIdPicture(request.getIdPicture());
        }
        if (null != request.getProfessionId()) {
            user.setProfessionId(request.getProfessionId());
        }
        if (StringUtils.isNotBlank(request.getInvestmentOrg())) {
            user.setInvestmentOrg(request.getInvestmentOrg());
        }
        if (StringUtils.isNotBlank(request.getInvestmentStyle())) {
            user.setInvestmentStyle(request.getInvestmentStyle());
        }
        user.setUpdated(new Date());

        userDao.update(user);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public UserDetailVO register(final UserAddRequest userAddRequest) throws CException {
        String account = userAddRequest.getAccount();
        boolean isMobile = ValidationUtil.isMobilePhoneNumber(account);
        User user = new User();
        user.setProfessionId(userAddRequest.getProfessionId());
        if (isMobile) {
            if (null != userDao.getUserByMobile(account)) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.MOBILE_REGISTERED);
            }

            String code = userAddRequest.getCode();
            UserAccountConfirmation userAccountConfirmation = userAccountConfirmationDao
                    .getUserAccountConfirmation(CommonConstant.MOBILE, account, code);
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
        user.setName(StringUtils.isNotBlank(userAddRequest.getName()) ? userAddRequest.getName() : account);
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
            // MD5Encrypt.MD5Encode(code);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userName", account);
            String verifyEmailURL = new StringBuilder(GlobalProperties.VERIFY_EMAIL_URL).append("?account=")
                    .append(account).append("&code=").append(code).toString();
            params.put("verifyEmailURL", verifyEmailURL);

            sendMail(userId, MessageType.REGISTER.getValue(), account, params);
        }

        Long imId = IMUtils.register(userId, account, GlobalProperties.DEFAULT_AVATAR_URL);
        if (0 != imId) {
            user.setImId(imId);
            userDao.update(user);
        }

        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setUserId(userId);
        userDetailVO.setRoleId(user.getRoleId());
        userDetailVO.setName(account);
        userDetailVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
        userDetailVO.setImId(imId);

        UserFriends userFriends = new UserFriends();
        UserFriendsId id = new UserFriendsId(userId, GlobalProperties.IM_ROBOT_ID);
        userFriends.setId(id);
        userFriends.setCreated(new Date());
        userFriends.setIsPassed(true);
        userFriendsDAO.save(userFriends);

        return userDetailVO;
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

        if (StringUtils.isNotBlank(profileUpdateRequest.getEmail())) {
            user.setEmail(profileUpdateRequest.getEmail());
        }
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

            String strDomains = strDomainIds.substring(0, strDomainIds.length() - 1);
            user.setDomains(strDomains);
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
        userDetailVO.setOriginAvatar(user.getAvatar());
        userDetailVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
        userDetailVO.setGender(user.getGender());
        userDetailVO.setAge(user.getAge());
        userDetailVO.setIntroduction(user.getIntroduction());
        userDetailVO.setIsMobileVerified(user.getIsMobileVerified());
        userDetailVO.setIsEmailVerified(user.getIsEmailVerified());
        userDetailVO.setImId(user.getImId());

        Long professionId = user.getProfessionId();
        userDetailVO.setProfessionId(professionId);
        if (professionId == 1) {
            userDetailVO.setProfessionName("学术型");
        } else if (professionId == 2) {
            userDetailVO.setProfessionName("实业型");
        }

        long roleId = user.getRoleId();
        userDetailVO.setRoleId(roleId);
        if (roleId == 1) {
            userDetailVO.setRoleName("创业者");
        } else if (roleId == 2) {
            userDetailVO.setRoleName("投资人");
        } else {
            userDetailVO.setRoleName("导师");
        }

        userDetailVO.setLocationId(user.getLocationId());
        userDetailVO.setFullLocation(user.getFullLocation());

        userDetailVO.setAuthenticated(user.getAuthenticated());
        userDetailVO.setIdNumber(user.getIdNumber());
        userDetailVO.setOriginIdPicture(user.getIdPicture());
        userDetailVO.setIdPicture(CDNUtil.getFullPath(user.getIdPicture()));
        userDetailVO.setAuthenticationInfo(user.getAuthenticationInfo());

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
        if (StringUtils.isNotBlank(user.getDomains())) {
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

        userDetailVO.setInvestmentOrg(user.getInvestmentOrg());
        userDetailVO.setInvestmentStyle(user.getInvestmentStyle());

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

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public LoginResponse loginUse3rdOauth(SignInByThirdPartRequest signInBy3rdPartRequest) throws CException {
        LoginResponse loginResponse = new LoginResponse();
        Date now = new Date();

        int providerId = signInBy3rdPartRequest.getProviderId();
        String uid = signInBy3rdPartRequest.getUid();
        String accessToken = signInBy3rdPartRequest.getAccessToken();
        String refreshToken = signInBy3rdPartRequest.getRefreshToken();
        Long expireIn = signInBy3rdPartRequest.getExpireIn();

        User user = null;

        User3rdAccountAccess user3rdAccountAccess = user3rdAccountAccessDao.getByProviderAndUid(providerId, uid);
        if (null == user3rdAccountAccess) {
            user = new User();

            String salt = PasswordUtil.genSalt();
            user.setName(signInBy3rdPartRequest.getNickname());
            if (StringUtils.isNoneEmpty(signInBy3rdPartRequest.getAvatar())) {
                user.setAvatar(signInBy3rdPartRequest.getAvatar());
            }
            user.setSalt(salt);
            user.setPassword(PasswordUtil.encodePassword(PasswordUtil.genPassword(10), salt));
            user.setClientIp(getIp());
            user.setCreated(now);

            setDefaultValue(user);

            userDao.save(user);

            long userId = user.getId();

            Long imId = IMUtils.register(userId, providerId + "_" + userId, GlobalProperties.DEFAULT_AVATAR_URL);
            if (0 != imId) {
                user.setImId(imId);
                userDao.update(user);
            }

            user3rdAccountAccess = new User3rdAccountAccess();
            user3rdAccountAccess.setUserId(userId);
            user3rdAccountAccess.setProviderId(providerId);
            user3rdAccountAccess.setUid(uid);
            user3rdAccountAccess.setAccessToken(accessToken);
            user3rdAccountAccess.setRefreshToken(refreshToken);
            user3rdAccountAccess.setExpires(expireIn);
            user3rdAccountAccess.setCreated(now);
            user3rdAccountAccessDao.save(user3rdAccountAccess);

            UserFriends userFriends = new UserFriends();
            UserFriendsId id = new UserFriendsId(userId, GlobalProperties.IM_ROBOT_ID);
            userFriends.setId(id);
            userFriends.setCreated(new Date());
            userFriends.setIsPassed(true);
            userFriendsDAO.save(userFriends);
        } else {
            long userId = user3rdAccountAccess.getUserId();

            user3rdAccountAccess.setAccessToken(signInBy3rdPartRequest.getAccessToken());
            user3rdAccountAccess.setRefreshToken(signInBy3rdPartRequest.getRefreshToken());
            user3rdAccountAccess.setExpires(signInBy3rdPartRequest.getExpireIn());
            user3rdAccountAccess.setUpdated(now);

            user = userDao.get(userId);
        }

        loginResponse.setUserId(user.getId());
        loginResponse.setRoleId(user.getRoleId());
        loginResponse.setName(user.getName());
        loginResponse.setImId(user.getImId());
        loginResponse.setAvatar(CDNUtil.getFullPath(user.getAvatar()));

        return loginResponse;
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
