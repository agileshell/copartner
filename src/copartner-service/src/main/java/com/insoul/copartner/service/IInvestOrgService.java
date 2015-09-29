package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.InvestOrgDetailVO;
import com.insoul.copartner.vo.InvestOrgVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.InvestOrgListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 下午12:10:57
 */
public interface IInvestOrgService {

    Pagination<InvestOrgVO> listInvestOrgs(InvestOrgListRequest requestData);

    InvestOrgDetailVO getInvestOrg(Long investOrgId) throws CException;
}