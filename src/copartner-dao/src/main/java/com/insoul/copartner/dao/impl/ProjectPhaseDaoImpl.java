package com.insoul.copartner.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.domain.ProjectPhase;

@Repository
public class ProjectPhaseDaoImpl extends BaseDaoImpl<ProjectPhase, Long> implements IProjectPhaseDao {

    @Override
    public List<ProjectPhase> getAllListed() {
        return queryByNamedQuery("ProjectPhase.getAllListed");
    }
}
