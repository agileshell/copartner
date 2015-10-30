package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.InvestOrgCriteria;
import com.insoul.copartner.domain.ServiceArch;

public interface IServiceArchDAO extends IBaseDao<ServiceArch, Long> {

    List<ServiceArch> queryServiceArch(InvestOrgCriteria criteria);

    Long countServiceArch(InvestOrgCriteria criteria);
}
