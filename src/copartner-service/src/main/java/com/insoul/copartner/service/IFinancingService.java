package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.FinancingVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.FinancingAddRequest;
import com.insoul.copartner.vo.request.FinancingListRequest;

public interface IFinancingService {

    Pagination<FinancingVO> listFinancings(FinancingListRequest requestData);

    void createFinancing(FinancingAddRequest requestData) throws CException;
}
