package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.DemandStatus;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IDemandCommentsDao;
import com.insoul.copartner.dao.IDemandDao;
import com.insoul.copartner.dao.IDemandLikersDao;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.ILocationDao;
import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.DemandCommentCriteria;
import com.insoul.copartner.dao.criteria.DemandCriteria;
import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.Demand;
import com.insoul.copartner.domain.DemandComments;
import com.insoul.copartner.domain.DemandLikers;
import com.insoul.copartner.domain.DemandLikersId;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Location;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.copartner.domain.TeamSize;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IDemandService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.ContentUtil;
import com.insoul.copartner.vo.CommentVO;
import com.insoul.copartner.vo.DemandDetailVO;
import com.insoul.copartner.vo.DemandVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.UserBriefVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.copartner.vo.request.DemandAddRequest;
import com.insoul.copartner.vo.request.DemandCommentRequest;
import com.insoul.copartner.vo.request.DemandListRequest;
import com.insoul.copartner.vo.request.PaginationRequest;

@Service
public class DemandServiceImpl extends BaseServiceImpl implements IDemandService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IStartupRoleDao startupRoleDao;

    @Resource
    private IDemandDao demandDao;

    @Resource
    private IDemandCommentsDao demandCommentsDao;

    @Resource
    private IDemandLikersDao demandLikersDao;

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IProjectPhaseDao projectPhaseDao;

    @Resource
    private IIndustryDomainDao industryDomainDao;

    @Resource
    private ITeamSizeDao teamSizeDao;

    @Override
    public Pagination<DemandVO> listDemands(DemandListRequest requestData) {
        DemandCriteria criteria = new DemandCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setUserId(requestData.getUserId());
        criteria.setFrom(
                (null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
        criteria.setProjectName(requestData.getKeyword());

        if (null != requestData.getUserId() && requestData.getUserId().equals(getUserId())) {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue(), DemandStatus.INACTIVE.getValue() });
        } else {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue() });
        }

        criteria.setBeused((byte) 0);

        List<Demand> demands = demandDao.queryDemand(criteria);
        Long count = demandDao.countDemand(criteria);
        return new Pagination<DemandVO>(formatDemands(demands), count);
    }

    @Override
    public DemandDetailVO getDemand(Long demandId) throws CException {
        Demand demand = demandDao.get(demandId);
        if (null == demand) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        DemandDetailVO demandVO = new DemandDetailVO();
        demandVO.setProjectName(demand.getProjectName());
        demandVO.setStatus(demand.getStatus());

        IndustryDomain industryDomain = industryDomainDao.get(demand.getIndustryDomainId());
        demandVO.setIndustryDomain(industryDomain.getName());
        demandVO.setIndustryDomainId(demand.getIndustryDomainId());

        TeamSize teamSize = teamSizeDao.get(demand.getTeamSizeId());
        demandVO.setTeamSizeId(demand.getTeamSizeId());
        demandVO.setTeamSize(teamSize.getName());

        demandVO.setLocationId(demand.getLocationId());
        demandVO.setLocation(demand.getFullLocation());

        demandVO.setHasBusinessRegistered(demand.getHasBusinessRegistered());
        demandVO.setAdvantage(demand.getAdvantage());
        demandVO.setContent(demand.getContent());
        demandVO.setReward(demand.getReward());
        demandVO.setCommentCount(demand.getCommentCount());
        demandVO.setLikeCount(demand.getLikeCount());
        demandVO.setContactPerson(demand.getContactPerson());
        demandVO.setContact(demand.getContact());

        demandVO.setProjectId(demand.getProjectId());
        demandVO.setBusinessLicense(demand.getBusinessLicense());
        demandVO.setBusinessLicenseUrl(CDNUtil.getFileFullPath(demand.getBusinessLicenseUrl()));
        demandVO.setBusinessPlan(CDNUtil.getFileFullPath(demand.getBusinessPlan()));

        User owner = userDao.get(demand.getUserId());
        UserBriefVO ownerVO = new UserBriefVO();
        ownerVO.setUserId(owner.getId());
        ownerVO.setName(owner.getName());
        ownerVO.setAvatar(CDNUtil.getFullPath(owner.getAvatar()));
        ownerVO.setLocation(owner.getFullLocation());
        if (null != owner.getStartupRoleId()) {
            StartupRole startupRole = startupRoleDao.get(owner.getStartupRoleId());
            ownerVO.setRole(startupRole.getName());
        }
        demandVO.setUser(ownerVO);

        List<DemandLikers> demandLikers = demandLikersDao.findByDemandId(demandId);
        Set<Long> likerIds = new HashSet<Long>();
        for (DemandLikers demandLiker : demandLikers) {
            likerIds.add(demandLiker.getId().getUserId());
        }
        Set<UserLeanVO> likers = new HashSet<UserLeanVO>();
        List<User> users = userDao.getUserByIds(likerIds);
        for (User user : users) {
            UserLeanVO userVO = new UserLeanVO();
            userVO.setUserId(user.getId());
            userVO.setName(user.getName());
            userVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));

            likers.add(userVO);
        }
        demandVO.setLikers(likers);

        return demandVO;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void createDemand(DemandAddRequest requestData) throws CException {
        long userId = getUserId();
        Demand demand = new Demand();

        IndustryDomain industryDomain = industryDomainDao.get(requestData.getIndustryDomainId());
        if (null == industryDomain) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.INDUSTRY_DOMAIN_NOT_EXIST);
        }
        demand.setIndustryDomainId(requestData.getIndustryDomainId());

        TeamSize teamSize = teamSizeDao.get(requestData.getTeamSizeId());
        if (null == teamSize) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.TEAM_SIZE_NOT_EXIST);
        }
        demand.setTeamSizeId(requestData.getTeamSizeId());

        Location location = locationDao.get(requestData.getLocationId());
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
        demand.setFullLocation(fullLocation.toString());
        demand.setLocationId(requestData.getLocationId());

        demand.setUserId(userId);
        demand.setProjectName(requestData.getProjectName());
        demand.setHasBusinessRegistered(requestData.getHasBusinessRegistered());
        demand.setAdvantage(requestData.getAdvantage());
        demand.setContent(requestData.getContent());
        demand.setReward(requestData.getReward());
        demand.setContactPerson(requestData.getContactPerson());
        demand.setContact(requestData.getContact());
        demand.setCreated(new Date());

        demand.setProjectId(requestData.getProjectId());
        demand.setBusinessLicense(requestData.getBusinessLicense());
        demand.setBusinessLicenseUrl(requestData.getBusinessLicenseUrl());
        demand.setBusinessPlan(requestData.getBusinessPlan());

        demandDao.save(demand);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void deleteDemand(Long demandId) throws CException {
        Demand demand = demandDao.get(demandId);
        if (null == demand) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        if (!(demand.getUserId().equals(getUserId()))) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_BELONG_CURRENTUSER);
        }

        demand.setStatus(DemandStatus.DELETED.getValue());
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void commentDemand(DemandCommentRequest requestData) throws CException {
        Demand demand = demandDao.get(requestData.getDemandId());
        if (null == demand) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        DemandComments demandComment = new DemandComments();
        demandComment.setUserId(getUserId());
        demandComment.setDemandId(requestData.getDemandId());
        demandComment.setContent(requestData.getContent());

        if (null != requestData.getParentId() && requestData.getParentId() > 0) {
            DemandComments parent = demandCommentsDao.get(requestData.getParentId());
            if (null != parent && parent.getDemandId().longValue() == requestData.getDemandId()) {
                demandComment.setParentId(requestData.getParentId());
            }
        }

        demandComment.setCreated(new Date());

        demandCommentsDao.save(demandComment);

        demand.setCommentCount(demand.getCommentCount() + 1);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void likeDemand(Long demandId) throws CException {
        Demand demand = demandDao.get(demandId);
        if (null == demand) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        long currentUserId = getUserId();

        if (null == demandLikersDao.findByIds(demandId, currentUserId)) {
            DemandLikers demandLikers = new DemandLikers();
            demandLikers.setId(new DemandLikersId(demandId, currentUserId));
            demandLikers.setCreated(new Date());

            demandLikersDao.save(demandLikers);

            demand.setLikeCount(demand.getLikeCount() + 1);
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void unlikeDemand(Long demandId) throws CException {
        Demand demand = demandDao.get(demandId);
        if (null == demand) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        long currentUserId = getUserId();
        DemandLikers demandLiker = demandLikersDao.findByIds(demandId, currentUserId);
        if (null != demandLiker) {
            demandLikersDao.delete(demandLiker);

            demand.setLikeCount(demand.getLikeCount() - 1);
        }
    }

    private List<DemandVO> formatDemands(List<Demand> demands) {
        List<DemandVO> demandVOs = new ArrayList<DemandVO>();
        if (demands == null || demands.isEmpty()) {
            return demandVOs;
        }

        Set<Long> demandIds = new HashSet<Long>();
        Set<Long> userIds = new HashSet<Long>();
        for (Demand demand : demands) {
            demandIds.add(demand.getId());
            userIds.add(demand.getUserId());
        }

        List<IndustryDomain> industryDomains = industryDomainDao.findAll();
        Map<Long, String> domainIdMapName = new HashMap<Long, String>();
        for (IndustryDomain industryDomain : industryDomains) {
            domainIdMapName.put(industryDomain.getId(), industryDomain.getName());
        }
        List<TeamSize> teamSizes = teamSizeDao.findAll();
        Map<Long, String> teamSizeIdMapName = new HashMap<Long, String>();
        for (TeamSize teamSize : teamSizes) {
            teamSizeIdMapName.put(teamSize.getId(), teamSize.getName());
        }

        List<StartupRole> startupRoles = startupRoleDao.findAll();
        Map<Long, String> roleIdMapName = new HashMap<Long, String>();
        for (StartupRole startupRole : startupRoles) {
            roleIdMapName.put(startupRole.getId(), startupRole.getName());
        }

        Map<Long, UserBriefVO> userIdMapUserVO = new HashMap<Long, UserBriefVO>();
        List<User> users = userDao.getUserByIds(userIds);
        for (User user : users) {
            UserBriefVO userVO = new UserBriefVO();
            userVO.setUserId(user.getId());
            userVO.setName(user.getName());
            userVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
            userVO.setLocation(user.getFullLocation());

            if (null != user.getStartupRoleId() && roleIdMapName.containsKey(user.getStartupRoleId())) {
                userVO.setRole(roleIdMapName.get(user.getStartupRoleId()));
            }

            userIdMapUserVO.put(user.getId(), userVO);
        }

        PaginationCriteria pagination = new PaginationCriteria();
        pagination.setOffset(0);
        pagination.setLimit(10);
        List<DemandLikers> demandLikers = demandLikersDao.findByDemandIdsAndPagination(demandIds, pagination);
        Set<Long> likerIds = new HashSet<Long>();
        Map<Long, Set<Long>> demandIdMapLikerIds = new HashMap<Long, Set<Long>>();
        for (DemandLikers demandLiker : demandLikers) {
            Long userId = demandLiker.getId().getUserId();
            Long demandId = demandLiker.getId().getDemandId();
            Set<Long> ids = demandIdMapLikerIds.containsKey(demandId) ? demandIdMapLikerIds.get(demandId)
                    : new HashSet<Long>();
            ids.add(userId);
            demandIdMapLikerIds.put(demandId, ids);

            likerIds.add(userId);
        }
        Map<Long, UserLeanVO> userIdMapUser = new HashMap<Long, UserLeanVO>();
        List<User> likerUsers = userDao.getUserByIds(likerIds);
        for (User user : likerUsers) {
            UserLeanVO userVO = new UserLeanVO();
            userVO.setUserId(user.getId());
            userVO.setName(user.getName());
            userVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));

            userIdMapUser.put(user.getId(), userVO);
        }

        for (Demand demand : demands) {
            DemandVO demandVO = new DemandVO();
            demandVO.setProjectName(demand.getProjectName());
            demandVO.setLocation(demand.getFullLocation());
            demandVO.setTeamSize(teamSizeIdMapName.get(demand.getTeamSizeId()));
            demandVO.setUser(userIdMapUserVO.get(demand.getUserId()));
            demandVO.setStatus(demand.getStatus());
            demandVO.setContent(ContentUtil.splitAndFilterString(demand.getContent(), 80));
            demandVO.setReward(demand.getReward());
            demandVO.setContactPerson(demand.getContactPerson());
            demandVO.setContact(demand.getContact());
            demandVO.setCommentCount(demand.getCommentCount());
            demandVO.setLikeCount(demand.getLikeCount());
            demandVO.setCreated(demand.getCreated());

            Set<UserLeanVO> likers = new HashSet<UserLeanVO>();
            Set<Long> ids = demandIdMapLikerIds.get(demand.getId());
            if (null != ids) {
                for (Long id : ids) {
                    likers.add(userIdMapUser.get(id));
                }
            }
            demandVO.setLikers(likers);

            demandVOs.add(demandVO);
        }

        return demandVOs;
    }

    @Override
    public Pagination<CommentVO> listComments(Long demandId, PaginationRequest requestData) {
        DemandCommentCriteria criteria = new DemandCommentCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setDemandId(demandId);

        List<DemandComments> comments = demandCommentsDao.queryComments(criteria);
        Long count = demandCommentsDao.countComments(criteria);
        return new Pagination<CommentVO>(formatComments(comments), count);
    }

    private List<CommentVO> formatComments(List<DemandComments> comments) {
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();

        Set<Long> commentorIds = new HashSet<Long>();
        for (DemandComments comment : comments) {
            commentorIds.add(comment.getUserId());
        }
        Map<Long, UserLeanVO> userIdMapUser = new HashMap<Long, UserLeanVO>();
        List<User> users = userDao.getUserByIds(commentorIds);
        for (User user : users) {
            UserLeanVO userVO = new UserLeanVO();
            userVO.setUserId(user.getId());
            userVO.setName(user.getName());
            userVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));

            userIdMapUser.put(user.getId(), userVO);
        }

        for (DemandComments comment : comments) {
            CommentVO commentVO = new CommentVO();
            commentVO.setId(comment.getId());
            commentVO.setParentId(comment.getParentId());
            commentVO.setContent(comment.getContent());
            commentVO.setCommentor(userIdMapUser.get(comment.getUserId()));

            commentVOs.add(commentVO);
        }

        return commentVOs;
    }
}
