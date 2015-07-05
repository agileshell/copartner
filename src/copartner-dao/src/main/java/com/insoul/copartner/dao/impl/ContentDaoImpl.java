package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IContentDao;
import com.insoul.copartner.dao.criteria.ContentCriteria;
import com.insoul.copartner.domain.Content;

@Repository
public class ContentDaoImpl extends BaseDaoImpl<Content, Long> implements IContentDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Content> queryContent(ContentCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countContent(ContentCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Query generateQuery(ContentCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getType() && criteria.getType() > 0) {
            conditionStr.append(" AND type = :type");
            params.put("type", criteria.getType());
        }
        if (null != criteria.getStatus()) {
            conditionStr.append(" AND status IN :status");
            params.put("status", new HashSet(Arrays.asList(criteria.getStatus())));
        }
        if (null != criteria.getId() && criteria.getId() > 0L) {
            conditionStr.append(" AND id  = :id");
            params.put("id", criteria.getId());
        }
        if (StringUtils.isNotBlank(criteria.getTitle())) {
            conditionStr.append(" AND title  like :title");
            params.put("title", "%" + criteria.getTitle() + "%");
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM Content WHERE 1=1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM Content WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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
