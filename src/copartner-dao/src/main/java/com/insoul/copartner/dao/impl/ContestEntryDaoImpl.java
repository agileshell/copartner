package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IContestEntryDAO;
import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.ContestEntry;

@Repository
public class ContestEntryDaoImpl extends BaseDaoImpl<ContestEntry, Long>implements IContestEntryDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<ContestEntry> queryContestEntry(ContestEntryCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countContestEntry(ContestEntryCriteria criteria) {
        return (Long) generateQuery(criteria, true).getSingleResult();
    }

    private Query generateQuery(ContestEntryCriteria criteria, boolean count) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getFrom()) {
            conditionStr.append(" AND created > :from");
            params.put("from", criteria.getFrom());
        }
        if (null != criteria.getTo()) {
            conditionStr.append(" AND created < :to");
            params.put("to", criteria.getTo());
        }
        if (criteria.getUserId() != null && criteria.getUserId() > 0L) {
            conditionStr.append(" AND userId = :userId");
            params.put("userId", criteria.getUserId());
        }
        if (criteria.getContestId() != null && criteria.getContestId() > 0L) {
            conditionStr.append(" AND contestId = :contestId");
            params.put("contestId", criteria.getContestId());
        }
        if (StringUtils.isNotBlank(criteria.getStatus())) {
            conditionStr.append(" AND status = :status");
            params.put("status", criteria.getStatus());
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (count) {
            hql.append("SELECT COUNT(*) FROM ContestEntry WHERE 1 = 1 ").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM ContestEntry WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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

    @Override
    public ContestEntry getByContestAndUser(Long contestId, Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("contestId", contestId);
        parameters.put("userId", userId);

        return queryOneByNamedQuery("ContestEntry.getByContestAndUser", parameters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ContestEntry> tutorVoteRanking(long contestId, int limit) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("contestId", contestId);

        Query query = createQuery("FROM ContestEntry WHERE contestId = :contestId ORDER BY tutorVotes DESC",
                parameters);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ContestEntry> investorVoteRanking(long contestId, int limit) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("contestId", contestId);

        Query query = createQuery("FROM ContestEntry WHERE contestId = :contestId ORDER BY investorVotes DESC",
                parameters);
        query.setMaxResults(limit);

        return query.getResultList();
    }
}
