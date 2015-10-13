package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IContestEntryVoteDao;
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

}
