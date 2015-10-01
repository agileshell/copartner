package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.ContestEntryDetailVO;
import com.insoul.copartner.vo.ContestEntryVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContestEntryListRequest;

public interface IContestEntryService {

    Pagination<ContestEntryVO> listContestEntries(ContestEntryListRequest requestData);

    ContestEntryDetailVO getContestEntry(Long contestEntryId) throws CException;
}
