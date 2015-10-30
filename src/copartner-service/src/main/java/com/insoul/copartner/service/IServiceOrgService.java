package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ServiceArchVO;
import com.insoul.copartner.vo.request.InvestOrgListRequest;

public interface IServiceOrgService {

    Pagination<ServiceArchVO> listServiceOrgs(InvestOrgListRequest requestData);

    ServiceArchVO getServiceOrg(Long ServiceOrgId) throws CException;
}
