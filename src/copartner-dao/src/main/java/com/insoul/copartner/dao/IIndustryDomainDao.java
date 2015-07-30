package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.IndustryDomain;

public interface IIndustryDomainDao extends IBaseDao<IndustryDomain, Long> {
    List<IndustryDomain> getAllListed();

}
