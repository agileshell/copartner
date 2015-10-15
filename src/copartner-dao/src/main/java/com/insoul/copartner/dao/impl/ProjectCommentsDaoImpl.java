package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
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
        StringBuilder conditionStr = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != criteria.getProjectId() && criteria.getProjectId() > 0L) {
			conditionStr.append(" AND projectId = :projectId ");
			params.put("projectId", criteria.getProjectId());
		}
		if (StringUtils.isNotBlank(criteria.getStatus())) {
			conditionStr.append(" AND status = :status ");
			params.put("status", criteria.getStatus());
		}
		Query query = null;
		StringBuilder hql = new StringBuilder();
		if (isCount) {
			hql.append("SELECT COUNT(*) FROM ProjectComments WHERE 1 = 1").append(conditionStr);
			query = createQuery(hql.toString(), params);
		} else {
			hql.append("FROM ProjectComments WHERE 1 = 1").append(conditionStr);
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
