package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.ContestDetailVO;
import com.insoul.copartner.vo.ContestVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContestListRequest;

public interface IContestService {

    Pagination<ContestVO> listContests(ContestListRequest requestData);

    ContestDetailVO getContest(Long contestId) throws CException;
}
