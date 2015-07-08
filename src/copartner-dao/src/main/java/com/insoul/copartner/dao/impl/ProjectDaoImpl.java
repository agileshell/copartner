package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.criteria.ProjectCriteria;
import com.insoul.copartner.domain.Project;

@Repository
public class ProjectDaoImpl extends BaseDaoImpl<Project, Long> implements IProjectDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Project> queryProject(ProjectCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countProject(ProjectCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }
    
    @Override
	public long count() {
		return ((java.math.BigInteger) createNativeQuery("SELECT COUNT(1) FROM project", new HashMap<String, Object>()).getSingleResult()).longValue();
	}

    private Query generateQuery(ProjectCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getUserId()) {
            conditionStr.append(" AND userId = :userId");
            params.put("userId", criteria.getUserId());
        }
        if (null != criteria.getStatus() && criteria.getStatus().length > 0) {
            conditionStr.append(" AND status IN(:status)");
            params.put("status", Arrays.asList(criteria.getStatus()));
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM Project WHERE 1=1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM Project WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
            query = createQuery(hql.toString(), params);
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
