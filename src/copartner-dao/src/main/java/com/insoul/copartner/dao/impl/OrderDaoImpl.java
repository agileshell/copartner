package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IOrderDao;
import com.insoul.copartner.dao.criteria.OrderCriteria;
import com.insoul.copartner.domain.Order;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order, Long>implements IOrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryOrder(OrderCriteria criteria) {
		return generateQuery(criteria, false).getResultList();
	}

	@Override
	public Long countOrder(OrderCriteria criteria) {
		Long count = (Long) generateQuery(criteria, true).getSingleResult();

		return count;
	}

	private Query generateQuery(OrderCriteria criteria, boolean isCount) {
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
			hql.append("SELECT COUNT(*) FROM Order WHERE 1=1").append(conditionStr);
			query = createQuery(hql.toString(), params);
		} else {
			hql.append("FROM Order WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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
