package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IContestDAO;
import com.insoul.copartner.dao.IContestEntryDAO;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.ContestCriteria;
import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.Contest;
import com.insoul.copartner.domain.ContestEntry;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Project;
import com.insoul.copartner.domain.ProjectPhase;
import com.insoul.copartner.domain.TeamSize;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IContestService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.ContestDetailVO;
import com.insoul.copartner.vo.ContestEntryDetailVO;
import com.insoul.copartner.vo.ContestEntryVO;
import com.insoul.copartner.vo.ContestVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ProjectLeanVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.copartner.vo.request.ContestEntryListRequest;
import com.insoul.copartner.vo.request.ContestListRequest;

@Service
public class ContestServiceImpl extends BaseServiceImpl implements IContestService {

    @Resource
    private IContestDAO contestDAO;

    @Resource
    private IContestEntryDAO contestEntryDAO;

    @Resource
    private IProjectDao projectDao;

    @Resource
    private IProjectPhaseDao projectPhaseDao;

    @Resource
    private IIndustryDomainDao industryDomainDao;

    @Resource
    private ITeamSizeDao teamSizeDao;

    @Resource
    private IUserDao userDao;

    @Override
    public Pagination<ContestVO> listContests(ContestListRequest requestData) {
        ContestCriteria criteria = new ContestCriteria();
        criteria.setStatus("active");
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);

        Long count = contestDAO.countContest(criteria);
        List<Contest> list = contestDAO.queryContest(criteria);
        List<ContestVO> VOs = new ArrayList<ContestVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Contest p : list) {
                ContestVO vo = new ContestVO();
                vo.setId(p.getId());
                vo.setCoverImg(CDNUtil.getFullPath(p.getCoverImg()));
                vo.setTitle(p.getTitle());
                vo.setCreated(p.getCreated());

                VOs.add(vo);
            }
        }

        return new Pagination<ContestVO>(VOs, count);
    }

    @Override
    public ContestDetailVO getContest(Long contestId) throws CException {
        Contest io = contestDAO.get(contestId);
        if (null == io || !io.getStatus().equals("active")) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CONTEST_NOT_EXIST);
        }

        ContestDetailVO detail = new ContestDetailVO();
        detail.setId(io.getId());
        detail.setCoverImg(CDNUtil.getFullPath(io.getCoverImg()));
        detail.setIntroduction(io.getIntroduction());
        detail.setTitle(io.getTitle());
        detail.setRules(io.getRules());
        detail.setRegistration(io.getRegistration());

        return detail;
    }

    @Override
    public ContestDetailVO getLatestContest() throws CException {
        ContestDetailVO detail = new ContestDetailVO();

        ContestCriteria criteria = new ContestCriteria();
        criteria.setStatus("active");
        criteria.setLimit(1);
        List<Contest> list = contestDAO.queryContest(criteria);
        if (null != list && list.size() > 0) {
            Contest io = list.get(0);

            detail.setId(io.getId());
            detail.setCoverImg(CDNUtil.getFullPath(io.getCoverImg()));
            detail.setIntroduction(io.getIntroduction());
            detail.setTitle(io.getTitle());
            detail.setRules(io.getRules());
            detail.setRegistration(io.getRegistration());
        }

        return detail;
    }

    @Override
    public Pagination<ContestEntryVO> listContestEntries(ContestEntryListRequest requestData) {
        ContestEntryCriteria criteria = new ContestEntryCriteria();
        criteria.setUserId(requestData.getUserId());
        criteria.setContestId(requestData.getContestId());
        criteria.setStatus("active");
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);

        Long count = contestEntryDAO.countContestEntry(criteria);
        List<ContestEntry> list = contestEntryDAO.queryContestEntry(criteria);

        List<ContestEntryVO> vos = new ArrayList<ContestEntryVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ContestEntry contestEntry : list) {
                ContestEntryVO vo = new ContestEntryVO();
                vo.setId(contestEntry.getId());
                vo.setVotes(contestEntry.getVotes());

                // TODO
                Project project = projectDao.get(contestEntry.getProjectId());
                ProjectLeanVO projectVO = new ProjectLeanVO();
                projectVO.setId(project.getId());
                projectVO.setName(project.getName());
                projectVO.setLogo(CDNUtil.getFullPath(project.getLogo()));
                projectVO.setLocation(project.getFullLocation());
                IndustryDomain industryDomain = industryDomainDao.get(project.getIndustryDomainId());
                projectVO.setIndustryDomain(industryDomain.getName());
                TeamSize teamSize = teamSizeDao.get(project.getTeamSizeId());
                projectVO.setTeamSize(teamSize.getName());
                ProjectPhase projectPhase = projectPhaseDao.get(project.getProjectPhaseId());
                projectVO.setProjectPhase(projectPhase.getName());
                vo.setProject(projectVO);

                vos.add(vo);
            }
        }

        return new Pagination<ContestEntryVO>(vos, count);
    }

    @Override
    public ContestEntryDetailVO getContestEntry(Long contestEntryId) throws CException {
        ContestEntry contestEntry = contestEntryDAO.get(contestEntryId);
        if (null == contestEntry || !contestEntry.getStatus().equals("active")) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CONTEST_ENTRY_NOT_EXIST);
        }

        ContestEntryDetailVO detail = new ContestEntryDetailVO();
        detail.setId(contestEntry.getId());
        detail.setVotes(contestEntry.getVotes());
        detail.setHasBusinessRegistered(contestEntry.getHasBusinessRegistered());
        detail.setBusinessLicense(contestEntry.getBusinessLicense());
        detail.setBusinessLicenseImg(CDNUtil.getFullPath(contestEntry.getBusinessLicenseImg()));

        Project project = projectDao.get(contestEntry.getProjectId());
        ProjectLeanVO projectVO = new ProjectLeanVO();
        projectVO.setId(project.getId());
        projectVO.setName(project.getName());
        projectVO.setLogo(CDNUtil.getFullPath(project.getLogo()));
        projectVO.setLocation(project.getFullLocation());
        IndustryDomain industryDomain = industryDomainDao.get(project.getIndustryDomainId());
        projectVO.setIndustryDomain(industryDomain.getName());
        TeamSize teamSize = teamSizeDao.get(project.getTeamSizeId());
        projectVO.setTeamSize(teamSize.getName());
        ProjectPhase projectPhase = projectPhaseDao.get(project.getProjectPhaseId());
        projectVO.setProjectPhase(projectPhase.getName());
        detail.setProject(projectVO);

        User owner = userDao.get(contestEntry.getUserId());
        UserLeanVO user = new UserLeanVO();
        user.setUserId(owner.getId());
        user.setName(owner.getName());
        user.setAvatar(CDNUtil.getFullPath(owner.getAvatar()));
        detail.setUser(user);

        return detail;
    }
}
