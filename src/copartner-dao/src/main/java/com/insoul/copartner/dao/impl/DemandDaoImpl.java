package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IDemandDao;
import com.insoul.copartner.dao.criteria.DemandCriteria;
import com.insoul.copartner.domain.Demand;

@Repository
public class DemandDaoImpl extends BaseDaoImpl<Demand, Long> implements IDemandDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Demand> queryDemand(DemandCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countDemand(DemandCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(DemandCriteria criteria, boolean isCount) {
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
        if (StringUtils.isNotBlank(criteria.getProjectName())) {
            conditionStr.append(" AND projectName LIKE :projectName ");
            params.put("projectName", "%" + criteria.getProjectName() + "%");
        }
        if (null != criteria.getFrom()) {
            conditionStr.append(" AND created > :from");
            params.put("from", criteria.getFrom());
        }
        if (null != criteria.getTo()) {
            conditionStr.append(" AND created < :to");
            params.put("to", criteria.getTo());
        }
        if (criteria.getBeused() <= 1) {
            conditionStr.append(" AND beused = :beused");
            params.put("beused", criteria.getBeused());
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM Demand WHERE 1=1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM Demand WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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
