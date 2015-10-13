package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.ContestEntry;

public interface IContestEntryDAO extends IBaseDao<ContestEntry, Long> {

    List<ContestEntry> queryContestEntry(ContestEntryCriteria criteria);

    Long countContestEntry(ContestEntryCriteria criteria);

    ContestEntry getByContestAndUser(Long contestId, Long userId);

    List<ContestEntry> tutorVoteRanking(long contestId, int limit);

    List<ContestEntry> investorVoteRanking(long contestId, int limit);
}