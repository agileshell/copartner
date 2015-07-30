package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IDemandCommentsDao;
import com.insoul.copartner.dao.criteria.DemandCommentCriteria;
import com.insoul.copartner.domain.DemandComments;

@Repository
public class DemandCommentsDaoImpl extends BaseDaoImpl<DemandComments, Long> implements IDemandCommentsDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<DemandComments> queryComments(DemandCommentCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countComments(DemandCommentCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(DemandCommentCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != criteria.getDemandId() && criteria.getDemandId() > 0L) {
			conditionStr.append(" AND demandId = :demandId ");
			params.put("demandId", criteria.getDemandId());
		}
		if (StringUtils.isNotBlank(criteria.getStatus())) {
			conditionStr.append(" AND status = :status ");
			params.put("status", criteria.getStatus());
		}
		Query query = null;
		StringBuilder hql = new StringBuilder();
		if (isCount) {
			hql.append("SELECT COUNT(*) FROM DemandComments WHERE 1 = 1").append(conditionStr);
			query = createQuery(hql.toString(), params);
		} else {
			hql.append("FROM DemandComments WHERE 1 = 1").append(conditionStr).append(" ORDER BY created DESC");
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
