package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IContestDAO;
import com.insoul.copartner.dao.criteria.ContestCriteria;
import com.insoul.copartner.domain.Contest;

@Repository
public class ContestDaoImpl extends BaseDaoImpl<Contest, Long>implements IContestDAO {

    @Override
    public String getContestName(Long id) {
        if (id == null || id <= 0L) {
            return "";
        }
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("id", id);
        return (String) createNativeQuery("SELECT title FROM contest WHERE id = :id", args).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contest> queryContest(ContestCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countContest(ContestCriteria criteria) {
        return (Long) generateQuery(criteria, true).getSingleResult();
    }

    private Query generateQuery(ContestCriteria criteria, boolean count) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(criteria.getTitle())) {
            conditionStr.append(" AND title LIKE :title ");
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
        if (StringUtils.isNotBlank(criteria.getStatus())) {
            conditionStr.append(" AND status = :status");
            params.put("status", criteria.getStatus());
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (count) {
            hql.append("SELECT COUNT(*) FROM Contest WHERE 1 = 1 ").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM Contest WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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