package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.ContestCriteria;
import com.insoul.copartner.domain.Contest;

public interface IContestDAO extends IBaseDao<Contest, Long> {
    List<Contest> queryContest(ContestCriteria criteria);

    Long countContest(ContestCriteria criteria);

    String getContestName(Long id);
}