package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public long count() {
        return ((java.math.BigInteger) createNativeQuery("SELECT COUNT(1) FROM content", new HashMap<String, Object>())
                .getSingleResult()).longValue();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Query generateQuery(ContentCriteria criteria, boolean isCount) {
        System.out.println(criteria.toString());
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getStatus()) {
            conditionStr.append(" AND status IN :status");
            params.put("status", new HashSet(Arrays.asList(criteria.getStatus())));
        }
        if (null != criteria.getId() && criteria.getId() > 0L) {
            conditionStr.append(" AND id  = :id");
            params.put("id", criteria.getId());
        }
        if (StringUtils.isNotBlank(criteria.getTitle())) {
            conditionStr.append(" AND title LIKE :title");
            params.put("title", "%" + criteria.getTitle() + "%");
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Content> findByContentIds(Set<Long> contentIds) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("contentIds", contentIds);

        Query query =
                createQuery("FROM Content WHERE id IN (:contentIds) ORDER BY created DESC",
                        parameters);

        return query.getResultList();
    }

}
