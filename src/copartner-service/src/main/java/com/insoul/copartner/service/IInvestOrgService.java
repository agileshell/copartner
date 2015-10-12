package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.InvestOrgDetailVO;
import com.insoul.copartner.vo.InvestOrgVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.InvestOrgListRequest;

public interface IInvestOrgService {

    Pagination<InvestOrgVO> listInvestOrgs(InvestOrgListRequest requestData);

    InvestOrgDetailVO getInvestOrg(Long investOrgId) throws CException;
}
