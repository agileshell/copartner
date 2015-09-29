package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.InvestOrgCriteria;
import com.insoul.copartner.domain.InvestOrg;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 上午11:27:29
 */
public interface IInvestOrgDAO extends IBaseDao<InvestOrg, Long> {

    List<InvestOrg> queryInvestOrg(InvestOrgCriteria criteria);

    Long countInvestOrg(InvestOrgCriteria criteria);
}