package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IProjectCommentsDao;
import com.insoul.copartner.dao.criteria.ProjectCommentCriteria;
import com.insoul.copartner.domain.ProjectComments;

@Repository
public class ProjectCommentsDaoImpl extends BaseDaoImpl<ProjectComments, Long> implements IProjectCommentsDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectComments> queryComments(ProjectCommentCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countComments(ProjectCommentCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(ProjectCommentCriteria criteria, boolean isCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectId", criteria.getProjectId());

        Query query = null;
        if (isCount) {
            String hql = "SELECT COUNT(*) FROM ProjectComments WHERE projectId = :projectId AND status = 'active'";
            query = createQuery(hql, params);
        } else {
            String hql = "FROM ProjectComments WHERE projectId = :projectId AND status = 'active' ORDER BY created DESC";
            query = createQuery(hql, params);
            if ((criteria.getLimit() != null) && (criteria.getLimit() != 0)) {
                query.setMaxResults(criteria.getLimit());
                if (criteria.getOffset() != null) {
                    query.setFirstResult(criteria.getOffset());
                }
            }
        }

        return query;
    }
}
