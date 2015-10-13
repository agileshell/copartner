package com.insoul.copartner.dao;

import com.insoul.copartner.domain.ContestEntryVote;

public interface IContestEntryVoteDao extends IBaseDao<ContestEntryVote, Long> {

    ContestEntryVote getByContestEntryAndVotor(Long contestEntryId, Long votorId);
}
