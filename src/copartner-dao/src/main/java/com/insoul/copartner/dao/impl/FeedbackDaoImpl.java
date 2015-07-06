package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IFeedbackDao;
import com.insoul.copartner.dao.criteria.FeedbackCriteria;
import com.insoul.copartner.domain.Feedback;

@SuppressWarnings("unchecked")
@Repository
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Long> implements IFeedbackDao {
	
	@Override
	public List<Feedback> query(FeedbackCriteria criteria) {
		return generateQuery(criteria, false).getResultList();
	}

	private Query generateQuery(FeedbackCriteria criteria, boolean isCount) {
		StringBuilder conditionStr = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != criteria.getUserId() && criteria.getUserId() > 0L) {
			conditionStr.append(" AND userId = :userId");
			params.put("userId", criteria.getUserId());
		}
		if (StringUtils.isNotBlank(criteria.getText())) {
			conditionStr.append(" AND text like :text");
			params.put("text", "%" + criteria.getText() + "%");
		}
		Query query = null;
		StringBuilder hql = new StringBuilder();
		if (isCount) {
			hql.append("SELECT COUNT(*) FROM Feedback WHERE 1 = 1").append(conditionStr);
			query = createQuery(hql.toString(), params);
		} else {
			hql.append("FROM Feedback WHERE 1 = 1").append(conditionStr).append(" ORDER BY created DESC");
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
