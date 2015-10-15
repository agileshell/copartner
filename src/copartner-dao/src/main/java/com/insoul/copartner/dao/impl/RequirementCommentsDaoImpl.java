package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IRequirementCommentsDao;
import com.insoul.copartner.dao.criteria.RequirementCommentCriteria;
import com.insoul.copartner.domain.RequirementComments;

@Repository
public class RequirementCommentsDaoImpl extends BaseDaoImpl<RequirementComments, Long> implements
        IRequirementCommentsDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<RequirementComments> queryComments(RequirementCommentCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countComments(RequirementCommentCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(RequirementCommentCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getRequirementId() && criteria.getRequirementId() > 0L) {
            conditionStr.append(" AND requirementId = :requirementId ");
            params.put("requirementId", criteria.getRequirementId());
        }
        if (StringUtils.isNotBlank(criteria.getStatus())) {
            conditionStr.append(" AND status = :status ");
            params.put("status", criteria.getStatus());
        }
        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM RequirementComments WHERE 1 = 1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM RequirementComments WHERE 1 = 1").append(conditionStr);
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
