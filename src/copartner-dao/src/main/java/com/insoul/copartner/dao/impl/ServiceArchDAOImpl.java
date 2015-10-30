package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IServiceArchDAO;
import com.insoul.copartner.dao.criteria.InvestOrgCriteria;
import com.insoul.copartner.domain.ServiceArch;

@Repository
public class ServiceArchDAOImpl extends BaseDaoImpl<ServiceArch, Long> implements IServiceArchDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<ServiceArch> queryServiceArch(InvestOrgCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countServiceArch(InvestOrgCriteria criteria) {
        return (Long) generateQuery(criteria, true).getSingleResult();
    }
    
    private Query generateQuery(InvestOrgCriteria criteria, boolean count) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
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
        if (count) {
            hql.append("SELECT COUNT(*) FROM ServiceArch WHERE 1 = 1 ").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM ServiceArch WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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
