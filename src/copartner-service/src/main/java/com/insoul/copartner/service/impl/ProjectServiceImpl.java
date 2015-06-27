package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.DemandStatus;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.ILocationDao;
import com.insoul.copartner.dao.IProjectCommentsDao;
import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.IProjectLikersDao;
import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.dao.criteria.ProjectCommentCriteria;
import com.insoul.copartner.dao.criteria.ProjectCriteria;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Location;
import com.insoul.copartner.domain.Project;
import com.insoul.copartner.domain.ProjectComments;
import com.insoul.copartner.domain.ProjectLikers;
import com.insoul.copartner.domain.ProjectLikersId;
import com.insoul.copartner.domain.ProjectPhase;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IProjectService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.ContentUtil;
import com.insoul.copartner.vo.CommentVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ProjectDetailVO;
import com.insoul.copartner.vo.ProjectVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.copartner.vo.request.PaginationRequest;
import com.insoul.copartner.vo.request.ProjectAddRequest;
import com.insoul.copartner.vo.request.ProjectCommentRequest;
import com.insoul.copartner.vo.request.ProjectListRequest;
import com.insoul.copartner.vo.request.ProjectUpdateRequest;

@Service
public class ProjectServiceImpl extends BaseServiceImpl implements IProjectService {

    @Resource
    private IProjectDao projectDao;

    @Resource
    private IProjectPhaseDao projectPhaseDao;

    @Resource
    private IIndustryDomainDao industryDomainDao;

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IProjectLikersDao projectLikersDao;

    @Resource
    private IProjectCommentsDao projectCommentsDao;

    @Resource
    private IUserDao userDao;

    @Override
    public Pagination<ProjectVO> listProjects(ProjectListRequest requestData) {
        ProjectCriteria criteria = new ProjectCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setUserId(requestData.getUserId());

        if (null != requestData.getUserId() && requestData.getUserId().equals(getUserId())) {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue(), DemandStatus.BANNED.getValue() });
        } else {
            criteria.setStatus(new String[] { DemandStatus.ACTIVE.getValue() });
        }

        List<Project> projects = projectDao.queryProject(criteria);
        Long count = projectDao.countProject(criteria);
        return new Pagination<ProjectVO>(formatProjects(projects), count);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void createProject(ProjectAddRequest requestData) throws CException {
        Project project = new Project();

        try {
            Date initDate = DateUtils.parseDate(requestData.getInitDate(), "yyyy-MM");
            project.setInitDate(initDate);
        } catch (Exception e) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        ProjectPhase projectPhase = projectPhaseDao.get(requestData.getProjectPhaseId());
        if (null == projectPhase) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.PROJECT_PHASE_NOT_EXIST);
        }
        project.setProjectPhaseId(requestData.getProjectPhaseId());

        IndustryDomain industryDomain = industryDomainDao.get(requestData.getIndustryDomainId());
        if (null == industryDomain) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.INDUSTRY_DOMAIN_NOT_EXIST);
        }
        project.setIndustryDomainId(requestData.getIndustryDomainId());

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
        project.setFullLocation(fullLocation.toString());
        project.setLocationId(requestData.getLocationId());

        project.setUserId(getUserId());
        project.setName(requestData.getName());
        project.setLogo(requestData.getLogo());
        project.setContent(requestData.getContent());
        project.setCreated(new Date());

        projectDao.save(project);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void likeProject(Long projectId) throws CException {
        Project project = projectDao.get(projectId);
        if (null == project) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.PROJECT_NOT_EXIST);
        }

        long currentUserId = getUserId();

        if (null == projectLikersDao.findByIds(projectId, currentUserId)) {
            ProjectLikers projectLikers = new ProjectLikers();
            projectLikers.setId(new ProjectLikersId(projectId, currentUserId));
            projectLikers.setCreated(new Date());

            projectLikersDao.save(projectLikers);

            project.setLikeCount(project.getLikeCount() + 1);
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void unlikeProject(Long projectId) throws CException {
        Project project = projectDao.get(projectId);
        if (null == project) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.PROJECT_NOT_EXIST);
        }

        long currentUserId = getUserId();
        ProjectLikers projectLikers = projectLikersDao.findByIds(projectId, currentUserId);
        if (null != projectLikers) {
            projectLikersDao.delete(projectLikers);

            project.setLikeCount(project.getLikeCount() - 1);
        }
    }

    private List<ProjectVO> formatProjects(List<Project> projects) {

        Set<Long> userIds = new HashSet<Long>();
        Set<Long> projectIds = new HashSet<Long>();
        for (Project project : projects) {
            userIds.add(project.getUserId());
            projectIds.add(project.getId());
        }
        List<ProjectPhase> projectPhases = projectPhaseDao.findAll();
        Map<Long, String> phaseIdMapName = new HashMap<Long, String>();
        for (ProjectPhase projectPhase : projectPhases) {
            phaseIdMapName.put(projectPhase.getId(), projectPhase.getName());
        }

        List<IndustryDomain> industryDomains = industryDomainDao.findAll();
        Map<Long, String> domainIdMapName = new HashMap<Long, String>();
        for (IndustryDomain industryDomain : industryDomains) {
            domainIdMapName.put(industryDomain.getId(), industryDomain.getName());
        }

        PaginationCriteria pagination = new PaginationCriteria();
        pagination.setOffset(0);
        pagination.setLimit(10);
        List<ProjectLikers> projectLikeres = projectLikersDao.findByProjectIdsAndPagination(projectIds, pagination);
        Set<Long> likerIds = new HashSet<Long>();
        Map<Long, Set<Long>> projectIdMapLikerIds = new HashMap<Long, Set<Long>>();
        for (ProjectLikers projectLiker : projectLikeres) {
            Long userId = projectLiker.getId().getUserId();
            Long projectId = projectLiker.getId().getProjectId();
            Set<Long> ids = projectIdMapLikerIds.containsKey(projectId) ? projectIdMapLikerIds.get(projectId)
                    : new HashSet<Long>();
            ids.add(userId);
            projectIdMapLikerIds.put(projectId, ids);

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

        List<ProjectVO> projectVOs = new ArrayList<ProjectVO>();
        for (Project project : projects) {
            ProjectVO projectVO = new ProjectVO();
            projectVO.setName(project.getName());
            projectVO.setLogo(CDNUtil.getFullPath(project.getLogo()));
            projectVO.setContent(ContentUtil.splitAndFilterString(project.getContent(), 80));
            projectVO.setCommentCount(project.getCommentCount());
            projectVO.setLikeCount(project.getLikeCount());
            projectVO.setLocation(project.getFullLocation());

            projectVO.setProjectPhase(phaseIdMapName.get(project.getProjectPhaseId()));
            projectVO.setIndustryDomain(domainIdMapName.get(project.getIndustryDomainId()));

            Set<UserLeanVO> likers = new HashSet<UserLeanVO>();
            Set<Long> ids = projectIdMapLikerIds.get(project.getId());
            for (Long id : ids) {
                likers.add(userIdMapUser.get(id));
            }
            projectVO.setLikers(likers);

            projectVOs.add(projectVO);
        }

        return projectVOs;

    }

    @Override
    public void commentProject(ProjectCommentRequest requestData) throws CException {
        Project project = projectDao.get(requestData.getProjectId());
        if (null == project) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.PROJECT_NOT_EXIST);
        }

        ProjectComments comment = new ProjectComments();
        comment.setUserId(getUserId());
        comment.setProjectId(requestData.getProjectId());
        comment.setContent(requestData.getContent());

        if (null != requestData.getParentId() && requestData.getParentId() > 0) {
            ProjectComments parent = projectCommentsDao.get(requestData.getParentId());
            if (null != parent && parent.getProjectId().longValue() == requestData.getProjectId()) {
                comment.setParentId(requestData.getParentId());
            }
        }

        comment.setCreated(new Date());

        projectCommentsDao.save(comment);

        project.setCommentCount(project.getCommentCount() + 1);
    }

    @Override
    public Pagination<CommentVO> listComments(Long projectId, PaginationRequest requestData) {
        ProjectCommentCriteria criteria = new ProjectCommentCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setProjectId(projectId);

        List<ProjectComments> comments = projectCommentsDao.queryComments(criteria);
        Long count = projectCommentsDao.countComments(criteria);
        return new Pagination<CommentVO>(formatComments(comments), count);
    }

    private List<CommentVO> formatComments(List<ProjectComments> comments) {
        List<CommentVO> commentVOs = new ArrayList<CommentVO>();

        Set<Long> commentorIds = new HashSet<Long>();
        for (ProjectComments comment : comments) {
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

        for (ProjectComments comment : comments) {
            CommentVO commentVO = new CommentVO();
            commentVO.setId(comment.getId());
            commentVO.setParentId(comment.getParentId());
            commentVO.setContent(comment.getContent());
            commentVO.setCommentor(userIdMapUser.get(comment.getUserId()));

            commentVOs.add(commentVO);
        }

        return commentVOs;
    }

    @Override
    public ProjectDetailVO getProject(Long projectId) throws CException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateProject(ProjectUpdateRequest requestData) throws CException {
        // TODO Auto-generated method stub

    }
}