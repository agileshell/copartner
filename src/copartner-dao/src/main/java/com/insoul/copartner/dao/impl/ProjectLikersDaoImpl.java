package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IProjectLikersDao;
import com.insoul.copartner.dao.criteria.PaginationCriteria;
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

    @Override
    public List<ProjectLikers> findByProjectId(Long projectId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("projectId", projectId);

        return queryByNamedQuery("ProjectLikers.getByProjectId", parameters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectLikers> findByProjectIdsAndPagination(Set<Long> projectIds, PaginationCriteria pagination) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("projectIds", projectIds);

        Query query = createQuery("FROM ProjectLikers WHERE id.projectId IN :projectIds ORDER BY created DESC",
                parameters);
        if ((pagination.getLimit() != null) && (pagination.getLimit() != 0)) {
            query.setMaxResults(pagination.getLimit());
            if (pagination.getOffset() != null) {
                query.setFirstResult(pagination.getOffset());
            }
        }

        return query.getResultList();
    }

}
