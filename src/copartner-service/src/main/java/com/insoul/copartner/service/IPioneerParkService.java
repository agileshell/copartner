package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.PioneerParkDetailVO;
import com.insoul.copartner.vo.PioneerParkVO;
import com.insoul.copartner.vo.request.PoineerParkListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 上午11:42:22
 */
public interface IPioneerParkService {

    Pagination<PioneerParkVO> listPioneerParks(PoineerParkListRequest requestData);

    PioneerParkDetailVO getPioneerPark(Long pioneerParkId) throws CException;
}