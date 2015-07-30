package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.ProjectPhase;

public interface IProjectPhaseDao extends IBaseDao<ProjectPhase, Long> {

    List<ProjectPhase> getAllListed();

}
