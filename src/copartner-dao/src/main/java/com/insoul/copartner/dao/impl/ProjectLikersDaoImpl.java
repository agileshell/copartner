package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IProjectLikersDao;
import com.insoul.copartner.domain.ProjectLikers;
import com.insoul.copartner.domain.ProjectLikersId;

@Repository
public class ProjectLikersDaoImpl extends BaseDaoImpl<ProjectLikers, ProjectLikersId> implements IProjectLikersDao {

    @Override
    public ProjectLikers findByIds(Long projectId, Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("projectId", projectId);

        return queryOneByNamedQuery("ProjectLikers.getByIds", parameters);
    }

}
