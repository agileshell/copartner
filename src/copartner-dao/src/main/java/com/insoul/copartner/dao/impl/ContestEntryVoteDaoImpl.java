package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IContestEntryVoteDao;
import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.ContestEntryVote;

@Repository
public class ContestEntryVoteDaoImpl extends BaseDaoImpl<ContestEntryVote, Long>implements IContestEntryVoteDao {

    @Override
    public ContestEntryVote getByContestEntryAndVotor(Long contestEntryId, Long votorId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("contestEntryId", contestEntryId);
        parameters.put("votorId", votorId);

        return queryOneByNamedQuery("ContestEntryVote.getByContestEntryAndVotor", parameters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ContestEntryVote> findByContestEntryId(Long contestEntryId, PaginationCriteria criteria) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("contestEntryId", contestEntryId);

        Query query = createQuery("FROM ContestEntryVote WHERE contestEntryId = :contestEntryId ORDER BY created DESC",
                parameters);
        if ((criteria.getLimit() != null) && (criteria.getLimit() != 0)) {
            query.setMaxResults(criteria.getLimit());
            if (criteria.getOffset() != null) {
                query.setFirstResult(criteria.getOffset());
            }
        }
        return query.getResultList();
    }

}
