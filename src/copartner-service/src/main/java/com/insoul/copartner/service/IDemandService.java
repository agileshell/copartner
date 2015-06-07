package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.CommentVO;
import com.insoul.copartner.vo.DemandDetailVO;
import com.insoul.copartner.vo.DemandVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.DemandAddRequest;
import com.insoul.copartner.vo.request.DemandCommentRequest;
import com.insoul.copartner.vo.request.DemandListRequest;
import com.insoul.copartner.vo.request.PaginationRequest;

public interface IDemandService {

    Pagination<DemandVO> listDemands(DemandListRequest requestData);

    DemandDetailVO getDemand(Long demandId) throws CException;

    void createDemand(DemandAddRequest requestData) throws CException;

    void deleteDemand(Long demandId) throws CException;

    void commentDemand(DemandCommentRequest requestData) throws CException;

    void likeDemand(Long demandId) throws CException;

    void unlikeDemand(Long demandId) throws CException;

    Pagination<CommentVO> listComments(Long demandId, PaginationRequest requestData);
}
