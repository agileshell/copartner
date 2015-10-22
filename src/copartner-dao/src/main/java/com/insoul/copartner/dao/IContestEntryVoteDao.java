package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.ContestEntryVote;

public interface IContestEntryVoteDao extends IBaseDao<ContestEntryVote, Long> {

	ContestEntryVote getByContestEntryAndVotor(Long contestEntryId, Long votorId);

	List<ContestEntryVote> findByContestEntryId(Long contestEntryId, PaginationCriteria criteria);
}
