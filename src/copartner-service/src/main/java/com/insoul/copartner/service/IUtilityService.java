package com.insoul.copartner.service;

import java.util.List;

import com.insoul.copartner.vo.FinancingPhaseVO;
import com.insoul.copartner.vo.IndustryDomainVO;
import com.insoul.copartner.vo.LocationVO;
import com.insoul.copartner.vo.ProjectPhaseVO;
import com.insoul.copartner.vo.StartupRoleVO;
import com.insoul.copartner.vo.StartupStatusVO;
import com.insoul.copartner.vo.TeamSizeVO;

public interface IUtilityService {

    List<LocationVO> listByParentId(Long parentId);

    List<IndustryDomainVO> listIndustryDomains();

    List<StartupRoleVO> listStartupRoles();

    List<StartupStatusVO> listStartupStatus();

    List<ProjectPhaseVO> listProjectPhases();

    void feedback(String text);

    List<TeamSizeVO> listTeamSizes();

    List<FinancingPhaseVO> listFinancingPhases();
}
