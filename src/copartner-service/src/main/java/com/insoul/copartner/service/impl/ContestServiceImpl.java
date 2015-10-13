package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IContestDAO;
import com.insoul.copartner.dao.IContestEntryDAO;
import com.insoul.copartner.dao.IContestEntryVoteDao;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.ContestCriteria;
import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.Contest;
import com.insoul.copartner.domain.ContestEntry;
import com.insoul.copartner.domain.ContestEntryVote;
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
import com.insoul.copartner.vo.ContestEntryLeanVO;
import com.insoul.copartner.vo.ContestEntryVO;
import com.insoul.copartner.vo.ContestVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ProjectDetailVO;
import com.insoul.copartner.vo.ProjectLeanVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.copartner.vo.request.ContestEntryListRequest;
import com.insoul.copartner.vo.request.ContestListRequest;
import com.insoul.copartner.vo.request.ContestRegisterRequest;

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

    @Resource
    private IContestEntryVoteDao contestEntryVoteDao;

    @Override
    public Pagination<ContestVO> listContests(ContestListRequest requestData) {
        ContestCriteria criteria = new ContestCriteria();
        criteria.setStatus("active");
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom(
                (null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
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
            detail.setRegistration(io.getRegistration());
            detail.setRegister(isRegister(io.getId()));

            List<ContestEntryLeanVO> tutorVoteRanking = new ArrayList<ContestEntryLeanVO>();
            List<ContestEntry> contestEntries = contestEntryDAO.tutorVoteRanking(io.getId(), 10);
            for (ContestEntry contestEntry : contestEntries) {
                ContestEntryLeanVO cl = new ContestEntryLeanVO();
                cl.setId(contestEntry.getId());
                cl.setVotes(contestEntry.getVotes());

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
                cl.setProject(projectVO);

                User owner = userDao.get(contestEntry.getUserId());
                UserLeanVO user = new UserLeanVO();
                user.setUserId(owner.getId());
                user.setName(owner.getName());
                user.setAvatar(CDNUtil.getFullPath(owner.getAvatar()));
                cl.setUser(user);

                tutorVoteRanking.add(cl);
            }
            detail.setTutorVoteRanking(tutorVoteRanking);

            List<ContestEntryLeanVO> investorVoteRanking = new ArrayList<ContestEntryLeanVO>();
            List<ContestEntry> entries = contestEntryDAO.investorVoteRanking(io.getId(), 10);
            for (ContestEntry contestEntry : entries) {
                ContestEntryLeanVO cl = new ContestEntryLeanVO();
                cl.setId(contestEntry.getId());
                cl.setVotes(contestEntry.getVotes());

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
                cl.setProject(projectVO);

                User owner = userDao.get(contestEntry.getUserId());
                UserLeanVO user = new UserLeanVO();
                user.setUserId(owner.getId());
                user.setName(owner.getName());
                user.setAvatar(CDNUtil.getFullPath(owner.getAvatar()));
                cl.setUser(user);

                investorVoteRanking.add(cl);
            }
            detail.setInvestorVoteRanking(investorVoteRanking);
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
        criteria.setFrom(
                (null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
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
        detail.setTutorVotes(contestEntry.getTutorVotes());
        detail.setInvestorVotes(contestEntry.getInvestorVotes());
        detail.setHasBusinessRegistered(contestEntry.getHasBusinessRegistered());
        detail.setBusinessLicense(contestEntry.getBusinessLicense());
        detail.setBusinessLicenseImg(CDNUtil.getFullPath(contestEntry.getBusinessLicenseImg()));
        detail.setVote(isVote(contestEntry.getId()));

        Project project = projectDao.get(contestEntry.getProjectId());
        ProjectDetailVO projectVO = new ProjectDetailVO();
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
        projectVO.setAdvantage(project.getAdvantage());
        projectVO.setContent(project.getContent());
        detail.setProject(projectVO);

        User owner = userDao.get(contestEntry.getUserId());
        UserLeanVO user = new UserLeanVO();
        user.setUserId(owner.getId());
        user.setName(owner.getName());
        user.setAvatar(CDNUtil.getFullPath(owner.getAvatar()));
        detail.setUser(user);

        return detail;
    }

    @Override
    public boolean isRegister(long contestId) {
        long currentUserId = getUserId();
        ContestEntry contestEntry = contestEntryDAO.getByContestAndUser(contestId, currentUserId);

        return (null != contestEntry);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void register(long contestId, ContestRegisterRequest requestData) {
        Contest contest = contestDAO.get(contestId);
        if (null == contest) {
            return;
        }

        long currentUserId = getUserId();
        ContestEntry contestEntry = contestEntryDAO.getByContestAndUser(contestId, currentUserId);
        if (null == contestEntry) {
            contestEntry = new ContestEntry();
            contestEntry.setContestId(contestId);
            contestEntry.setProjectId(requestData.getProjectId());
            contestEntry.setUserId(currentUserId);
            contestEntry.setHasBusinessRegistered(requestData.getHasBusinessRegistered());
            contestEntry.setBusinessLicense(requestData.getBusinessLicense());
            contestEntry.setBusinessLicenseImg(requestData.getBusinessLicenseImg());
            contestEntry.setCreated(new Date());

            contestEntryDAO.save(contestEntry);
        }
    }

    @Override
    public boolean isVote(long contestEntryId) {
        long currentUserId = getUserId();
        ContestEntryVote contestEntryVote = contestEntryVoteDao.getByContestEntryAndVotor(contestEntryId,
                currentUserId);

        return (null != contestEntryVote);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void vote(long contestEntryId) {
        ContestEntry contestEntry = contestEntryDAO.get(contestEntryId);
        if (null == contestEntry) {
            return;
        }

        long currentUserId = getUserId();
        User user = userDao.get(currentUserId);
        long roleId = user.getRoleId();
        if (roleId == 1) {
            return;
        }

        ContestEntryVote contestEntryVote = contestEntryVoteDao.getByContestEntryAndVotor(contestEntryId,
                currentUserId);
        if (null == contestEntryVote) {
            contestEntryVote = new ContestEntryVote();
            contestEntryVote.setContestEntryId(contestEntryId);
            contestEntryVote.setVotorId(currentUserId);
            contestEntryVote.setCreated(new Date());

            contestEntryVoteDao.save(contestEntryVote);

            contestEntry.setVotes(contestEntry.getVotes() + 1);
            if (roleId == 2) {
                contestEntry.setInvestorVotes(contestEntry.getInvestorVotes() + 1);
            } else if (roleId == 3) {
                contestEntry.setTutorVotes(contestEntry.getTutorVotes() + 1);
            }
            contestEntry.setUpdated(new Date());
        }

    }
}
