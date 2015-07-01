package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import com.insoul.copartner.dao.INewsDao;
import com.insoul.copartner.dao.criteria.NewsCriteria;
import com.insoul.copartner.domain.News;

public class NewsDaoImpl extends BaseDaoImpl<News, Long> implements INewsDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<News> queryNews(NewsCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countNews(NewsCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(NewsCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getType()) {
            conditionStr.append(" AND type = :type");
            params.put("type", criteria.getType());
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM News WHERE 1=1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM News WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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
