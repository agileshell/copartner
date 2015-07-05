package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.dao.IFeedbackDao;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.ILocationDao;
import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.IStartupStatusDao;
import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.domain.Feedback;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Location;
import com.insoul.copartner.domain.ProjectPhase;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.copartner.domain.StartupStatus;
import com.insoul.copartner.domain.TeamSize;
import com.insoul.copartner.service.IUtilityService;
import com.insoul.copartner.vo.IndustryDomainVO;
import com.insoul.copartner.vo.LocationVO;
import com.insoul.copartner.vo.ProjectPhaseVO;
import com.insoul.copartner.vo.StartupRoleVO;
import com.insoul.copartner.vo.StartupStatusVO;
import com.insoul.copartner.vo.TeamSizeVO;

@Service
public class UtilityServiceImpl extends BaseServiceImpl implements IUtilityService {

    @Resource
    private ILocationDao locationDao;

    @Resource
    private IIndustryDomainDao industryDomainDao;

    @Resource
    private IStartupRoleDao startupRoleDao;

    @Resource
    private IStartupStatusDao startupStatusDao;

    @Resource
    private IProjectPhaseDao projectPhaseDao;

    @Resource
    private IFeedbackDao feedbackDao;

    @Resource
    private ITeamSizeDao teamSizeDao;

    @Override
    public List<LocationVO> listByParentId(Long parentId) {
        List<LocationVO> vos = new ArrayList<LocationVO>();

        List<Location> entities = locationDao.findByParentId(parentId);
        for (Location location : entities) {
            LocationVO vo = new LocationVO();
            vo.setLocationId(location.getId());
            vo.setParentId(location.getParentId());
            vo.setName(location.getName());
            vo.setPinyin(location.getPinyin());
            vo.setLatitude(location.getLatitude());
            vo.setLongitude(location.getLongitude());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    public List<IndustryDomainVO> listIndustryDomains() {
        List<IndustryDomainVO> vos = new ArrayList<IndustryDomainVO>();

        List<IndustryDomain> entities = industryDomainDao.getAllListed();
        for (IndustryDomain industryDomain : entities) {
            IndustryDomainVO vo = new IndustryDomainVO();
            vo.setId(industryDomain.getId());
            vo.setName(industryDomain.getName());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    public List<StartupRoleVO> listStartupRoles() {
        List<StartupRoleVO> vos = new ArrayList<StartupRoleVO>();

        List<StartupRole> entities = startupRoleDao.getAllListed();
        for (StartupRole startupRole : entities) {
            StartupRoleVO vo = new StartupRoleVO();
            vo.setId(startupRole.getId());
            vo.setName(startupRole.getName());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    public List<StartupStatusVO> listStartupStatus() {
        List<StartupStatusVO> vos = new ArrayList<StartupStatusVO>();

        List<StartupStatus> entities = startupStatusDao.getAllListed();
        for (StartupStatus startupStatus : entities) {
            StartupStatusVO vo = new StartupStatusVO();
            vo.setId(startupStatus.getId());
            vo.setName(startupStatus.getName());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    public List<ProjectPhaseVO> listProjectPhases() {
        List<ProjectPhaseVO> vos = new ArrayList<ProjectPhaseVO>();

        List<ProjectPhase> entities = projectPhaseDao.getAllListed();
        for (ProjectPhase projectPhase : entities) {
            ProjectPhaseVO vo = new ProjectPhaseVO();
            vo.setId(projectPhase.getId());
            vo.setName(projectPhase.getName());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void feedback(String text) {
        Feedback feedback = new Feedback();
        feedback.setUserId(getUserId());
        feedback.setText(text);
        feedback.setCreated(new Date());

        feedbackDao.save(feedback);
    }

    @Override
    public List<TeamSizeVO> listTeamSizes() {
        List<TeamSizeVO> vos = new ArrayList<TeamSizeVO>();

        List<TeamSize> entities = teamSizeDao.getAllListed();
        for (TeamSize teamSize : entities) {
            TeamSizeVO vo = new TeamSizeVO();
            vo.setId(teamSize.getId());
            vo.setName(teamSize.getName());

            vos.add(vo);
        }

        return vos;
    }

}
