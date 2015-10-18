package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.ContestDetailVO;
import com.insoul.copartner.vo.ContestEntryDetailVO;
import com.insoul.copartner.vo.ContestEntryVO;
import com.insoul.copartner.vo.ContestVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContestEntryListRequest;
import com.insoul.copartner.vo.request.ContestListRequest;
import com.insoul.copartner.vo.request.ContestRegisterRequest;

public interface IContestService {

    Pagination<ContestVO> listContests(ContestListRequest requestData);

    ContestDetailVO getContest(Long contestId) throws CException;

    ContestDetailVO getLatestContest() throws CException;

    Pagination<ContestEntryVO> listContestEntries(ContestEntryListRequest requestData);

    ContestEntryDetailVO getContestEntry(Long contestEntryId) throws CException;

    boolean isRegister(long contestId);

    void register(long contestId, ContestRegisterRequest requestData);

    boolean isVote(long contestEntryId);

    void vote(long contestEntryId, String comment);
}
