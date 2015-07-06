package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.ProjectPhaseCriteria;
import com.insoul.copartner.domain.ProjectPhase;

public interface IProjectPhaseDao extends IBaseDao<ProjectPhase, Long> {

    List<ProjectPhase> getAllListed();
    
    List<ProjectPhase> query(ProjectPhaseCriteria criteria);
}
