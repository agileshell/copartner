package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.PioneerParkDetailVO;
import com.insoul.copartner.vo.PioneerParkVO;
import com.insoul.copartner.vo.request.PoineerParkListRequest;

public interface IPioneerParkService {

    Pagination<PioneerParkVO> listPioneerParks(PoineerParkListRequest requestData);

    PioneerParkDetailVO getPioneerPark(Long pioneerParkId) throws CException;
}
