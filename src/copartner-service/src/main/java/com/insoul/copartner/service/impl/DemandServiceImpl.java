package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.http.impl.cookie.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.DemandStatus;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IDemandCommentsDao;
import com.insoul.copartner.dao.IDemandDao;
import com.insoul.copartner.dao.IDemandLikersDao;
import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.DemandCriteria;
import com.insoul.copartner.domain.Demand;
import com.insoul.copartner.domain.DemandComments;
import com.insoul.copartner.domain.DemandLikers;
import com.insoul.copartner.domain.DemandLikersId;
import com.insoul.copartner.domain.Project;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IDemandService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.ContentUtil;
import com.insoul.copartner.vo.DemandDetailVO;
import com.insoul.copartner.vo.DemandVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ProjectVO;
import com.insoul.copartner.vo.UserBriefVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.copartner.vo.request.DemandAddRequest;
import com.insoul.copartner.vo.request.DemandCommentRequest;
import com.insoul.copartner.vo.request.DemandListRequest;

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
    private IProjectDao projectDao;

    @Override
    public Pagination<DemandVO> listDemands(DemandListRequest requestData) {
        DemandCriteria criteria = new DemandCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setUserId(requestData.getUserId());
        criteria.setType(requestData.getType());

        if (null != requestData.getUserId() && requestData.getUserId().equals(getUserId())) {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue(), DemandStatus.BANNED.getValue() });
        } else {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue() });
        }

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
        demandVO.setStatus(demand.getStatus());
        demandVO.setContent(demand.getContent());
        demandVO.setType(demand.getType());
        demandVO.setCommentCount(demand.getCommentCount());
        demandVO.setLikeCount(demand.getLikeCount());
        demandVO.setCreated(DateUtils.formatDate(demand.getCreated(), CommonConstant.DATE_FORMAT_LONG));

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

        if (null != demand.getProjectId()) {
            Project project = projectDao.get(demand.getProjectId());
            if (null != project) {
                ProjectVO projectVO = new ProjectVO();
                projectVO.setName(project.getName());
                projectVO.setLogo(CDNUtil.getFullPath(project.getLogo()));
                projectVO.setContent(ContentUtil.splitAndFilterString(project.getContent(), 80));
                projectVO.setLocation(project.getFullLocation());

                demandVO.setProject(projectVO);
            }
        }

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

        // TODO set comments

        return demandVO;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void createDemand(DemandAddRequest requestData) throws CException {
        long userId = getUserId();
        Demand demand = new Demand();
        demand.setUserId(userId);
        demand.setContent(requestData.getContent());
        demand.setType(requestData.getType());
        if (requestData.getType() > 1) {
            Project project = projectDao.get(requestData.getProjectId());
            if (null == project) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.PROJECT_NOT_EXIST);
            }
            if (!(project.getUserId().equals(userId))) {
                throw CExceptionFactory.getException(CException.class, ResponseCode.PROJECT_NOT_BELONG_CURRENTUSER);
            }

            demand.setProjectId(requestData.getProjectId());
        }
        demand.setCreated(new Date());

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

        Set<Long> userIds = new HashSet<Long>();
        for (Demand demand : demands) {
            userIds.add(demand.getUserId());
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

        List<DemandVO> demandVOs = new ArrayList<DemandVO>();
        for (Demand demand : demands) {
            DemandVO demandVO = new DemandVO();
            demandVO.setUser(userIdMapUserVO.get(demand.getUserId()));
            demandVO.setStatus(demand.getStatus());
            demandVO.setContent(ContentUtil.splitAndFilterString(demand.getContent(), 80));
            demandVO.setType(demand.getType());
            demandVO.setCommentCount(demand.getCommentCount());
            demandVO.setLikeCount(demand.getLikeCount());
            demandVO.setCreated(DateUtils.formatDate(demand.getCreated(), CommonConstant.DATE_FORMAT_LONG));

            // TODO setLikers

            demandVOs.add(demandVO);
        }

        return demandVOs;
    }

}
