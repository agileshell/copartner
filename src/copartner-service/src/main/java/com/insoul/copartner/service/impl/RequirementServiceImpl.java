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
import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.IRequirementCommentsDao;
import com.insoul.copartner.dao.IRequirementDao;
import com.insoul.copartner.dao.IRequirementLikersDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.dao.criteria.RequirementCommentCriteria;
import com.insoul.copartner.dao.criteria.RequirementCriteria;
import com.insoul.copartner.domain.Project;
import com.insoul.copartner.domain.Requirement;
import com.insoul.copartner.domain.RequirementComments;
import com.insoul.copartner.domain.RequirementLikers;
import com.insoul.copartner.domain.RequirementLikersId;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IRequirementService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.ContentUtil;
import com.insoul.copartner.vo.CommentVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.RequirementDetailVO;
import com.insoul.copartner.vo.RequirementVO;
import com.insoul.copartner.vo.UserBriefVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.copartner.vo.request.PaginationRequest;
import com.insoul.copartner.vo.request.RequirementAddRequest;
import com.insoul.copartner.vo.request.RequirementCommentRequest;
import com.insoul.copartner.vo.request.RequirementListRequest;

@Service
public class RequirementServiceImpl extends BaseServiceImpl implements IRequirementService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IRequirementDao requirementDao;

    @Resource
    private IRequirementCommentsDao requirementCommentsDao;

    @Resource
    private IRequirementLikersDao requirementLikersDao;

    @Resource
    private IStartupRoleDao startupRoleDao;

    @Resource
    private IProjectDao projectDao;

    @Override
    public Pagination<RequirementVO> listRequirements(RequirementListRequest requestData) {
        RequirementCriteria criteria = new RequirementCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setUserId(requestData.getUserId());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);

        if (null != requestData.getUserId() && requestData.getUserId().equals(getUserId())) {
            criteria.setStatus(new String[] {DemandStatus.ACTIVE.getValue(), DemandStatus.INACTIVE.getValue()});
        } else {
            criteria.setStatus(new String[] {DemandStatus.ACTIVE.getValue()});
        }

        List<Requirement> requirements = requirementDao.queryRequirement(criteria);
        Long count = requirementDao.countRequirement(criteria);
        return new Pagination<RequirementVO>(formatRequirements(requirements), count);
    }

    private List<RequirementVO> formatRequirements(List<Requirement> requirements) {
        List<RequirementVO> requirementVOs = new ArrayList<RequirementVO>();
        if (requirements == null || requirements.isEmpty()) {
            return requirementVOs;
        }

        Set<Long> requirementIds = new HashSet<Long>();
        Set<Long> userIds = new HashSet<Long>();
        for (Requirement requirement : requirements) {
            requirementIds.add(requirement.getId());
            userIds.add(requirement.getUserId());
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
        List<RequirementLikers> requirementLikers =
                requirementLikersDao.findByRequirementIdsAndPagination(requirementIds, pagination);
        Set<Long> likerIds = new HashSet<Long>();
        Map<Long, Set<Long>> requirementIdMapLikerIds = new HashMap<Long, Set<Long>>();
        for (RequirementLikers requirementLiker : requirementLikers) {
            Long userId = requirementLiker.getId().getUserId();
            Long requirementId = requirementLiker.getId().getRequirementId();
            Set<Long> ids =
                    requirementIdMapLikerIds.containsKey(requirementId) ? requirementIdMapLikerIds.get(requirementId)
                            : new HashSet<Long>();
            ids.add(userId);
            requirementIdMapLikerIds.put(requirementId, ids);

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

        for (Requirement requirement : requirements) {
            RequirementVO requirementVO = new RequirementVO();
            requirementVO.setUser(userIdMapUserVO.get(requirement.getUserId()));
            requirementVO.setStatus(requirement.getStatus());
            requirementVO.setContent(ContentUtil.splitAndFilterString(requirement.getContent(), 80));
            requirementVO.setCommentCount(requirement.getCommentCount());
            requirementVO.setLikeCount(requirement.getLikeCount());
            requirementVO.setCreated(requirement.getCreated());

            Set<UserLeanVO> likers = new HashSet<UserLeanVO>();
            Set<Long> ids = requirementIdMapLikerIds.get(requirement.getId());
            if (null != ids) {
                for (Long id : ids) {
                    likers.add(userIdMapUser.get(id));
                }
            }
            requirementVO.setLikers(likers);

            requirementVOs.add(requirementVO);
        }

        return requirementVOs;
    }

    @Override
    public RequirementDetailVO getRequirement(Long requirementId) throws CException {
        Requirement requirement = requirementDao.get(requirementId);
        if (null == requirement) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        RequirementDetailVO requirementdVO = new RequirementDetailVO();
        requirementdVO.setId(requirement.getId());
        requirementdVO.setType(requirement.getType());
        requirementdVO.setStatus(requirement.getStatus());
        requirementdVO.setContent(requirement.getContent());

        User owner = userDao.get(requirement.getUserId());
        UserBriefVO ownerVO = new UserBriefVO();
        ownerVO.setUserId(owner.getId());
        ownerVO.setName(owner.getName());
        ownerVO.setAvatar(CDNUtil.getFullPath(owner.getAvatar()));
        ownerVO.setLocation(owner.getFullLocation());
        if (null != owner.getStartupRoleId()) {
            StartupRole startupRole = startupRoleDao.get(owner.getStartupRoleId());
            ownerVO.setRole(startupRole.getName());
        }
        requirementdVO.setUser(ownerVO);

        List<RequirementLikers> requirementLikers = requirementLikersDao.findByRequirementId(requirementId);
        Set<Long> likerIds = new HashSet<Long>();
        for (RequirementLikers requirementLiker : requirementLikers) {
            likerIds.add(requirementLiker.getId().getUserId());
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
        requirementdVO.setLikers(likers);

        return requirementdVO;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void createRequirement(RequirementAddRequest requestData) throws CException {
        long userId = getUserId();

        Project project = projectDao.get(requestData.getProjectId());
        if (null == project || !(project.getUserId().equals(userId))) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.PROJECT_NOT_EXIST);
        }

        Requirement requirement = new Requirement();
        requirement.setType(requestData.getType());
        requirement.setUserId(userId);
        requirement.setProjectId(requestData.getProjectId());
        requirement.setContent(requestData.getContent());
        requirement.setCreated(new Date());

        requirementDao.save(requirement);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void deleteRequirement(Long requirementId) throws CException {
        Requirement requirement = requirementDao.get(requirementId);
        if (null == requirement) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        if (!(requirement.getUserId().equals(getUserId()))) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_BELONG_CURRENTUSER);
        }

        requirement.setStatus(DemandStatus.DELETED.getValue());
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void commentRequirement(RequirementCommentRequest requestData) throws CException {
        Requirement requirement = requirementDao.get(requestData.getRequirementId());
        if (null == requirement) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        RequirementComments requirementComment = new RequirementComments();
        requirementComment.setUserId(getUserId());
        requirementComment.setRequirementId(requestData.getRequirementId());
        requirementComment.setContent(requestData.getContent());

        if (null != requestData.getParentId() && requestData.getParentId() > 0) {
            RequirementComments parent = requirementCommentsDao.get(requestData.getParentId());
            if (null != parent && parent.getRequirementId().longValue() == requestData.getRequirementId()) {
                requirementComment.setParentId(requestData.getParentId());
            }
        }

        requirementComment.setCreated(new Date());

        requirementCommentsDao.save(requirementComment);

        requirement.setCommentCount(requirement.getCommentCount() + 1);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void likeRequirement(Long requirementId) throws CException {
        Requirement requirement = requirementDao.get(requirementId);
        if (null == requirement) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        long currentUserId = getUserId();
        if (null == requirementLikersDao.findByIds(requirementId, currentUserId)) {
            RequirementLikers requirementLikers = new RequirementLikers();
            requirementLikers.setId(new RequirementLikersId(requirementId, currentUserId));
            requirementLikers.setCreated(new Date());

            requirementLikersDao.save(requirementLikers);

            requirement.setLikeCount(requirement.getLikeCount() + 1);
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void unlikeRequirement(Long requirementId) throws CException {
        Requirement requirement = requirementDao.get(requirementId);
        if (null == requirement) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.DEMAND_NOT_EXIST);
        }

        long currentUserId = getUserId();
        RequirementLikers requirementLiker = requirementLikersDao.findByIds(requirementId, currentUserId);
        if (null != requirementLiker) {
            requirementLikersDao.delete(requirementLiker);

            requirement.setLikeCount(requirement.getLikeCount() - 1);
        }
    }

    @Override
    public Pagination<CommentVO> listComments(Long requirementId, PaginationRequest requestData) {
        RequirementCommentCriteria criteria = new RequirementCommentCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setRequirementId(requirementId);

        List<RequirementComments> comments = requirementCommentsDao.queryComments(criteria);
        Long count = requirementCommentsDao.countComments(criteria);
        return new Pagination<CommentVO>(formatComments(comments), count);
    }

    private List<CommentVO> formatComments(List<RequirementComments> comments) {
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();

        Set<Long> commentorIds = new HashSet<Long>();
        for (RequirementComments comment : comments) {
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

        for (RequirementComments comment : comments) {
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