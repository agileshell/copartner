package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.criteria.ProjectCriteria;
import com.insoul.copartner.domain.Project;

@Repository
public class ProjectDaoImpl extends BaseDaoImpl<Project, Long> implements IProjectDao {

    @Override
    public String getProjectName(Long id) {
        if (id == null || id <= 0L) {
            return "";
        }
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("id", id);
        return (String) createNativeQuery("SELECT name FROM project WHERE id = :id", args).getSingleResult();
    }

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
        return ((java.math.BigInteger) createNativeQuery("SELECT COUNT(1) FROM project", new HashMap<String, Object>())
                .getSingleResult()).longValue();
    }

    private Query generateQuery(ProjectCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getUserId() && criteria.getUserId() > 0L) {
            conditionStr.append(" AND userId = :userId");
            params.put("userId", criteria.getUserId());
        }
        if (null != criteria.getProjectPhaseId() && 0 != criteria.getProjectPhaseId()) {
            conditionStr.append(" AND projectPhaseId = :projectPhaseId");
            params.put("projectPhaseId", criteria.getProjectPhaseId());
        }
        if (null != criteria.getStatus() && criteria.getStatus().length > 0) {
            conditionStr.append(" AND status IN(:status)");
            params.put("status", Arrays.asList(criteria.getStatus()));
        }
        if (StringUtils.isNotBlank(criteria.getName())) {
            conditionStr.append(" AND name LIKE :name ");
            params.put("name", "%" + criteria.getName() + "%");
        }
        if (null != criteria.getFrom()) {
            conditionStr.append(" AND created > :from");
            params.put("from", criteria.getFrom());
        }
        if (null != criteria.getTo()) {
            conditionStr.append(" AND created < :to");
            params.put("to", criteria.getTo());
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
