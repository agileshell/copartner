package com.insoul.copartner.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.domain.IndustryDomain;

@Repository
public class IndustryDomainDaoImpl extends BaseDaoImpl<IndustryDomain, Long> implements IIndustryDomainDao {

    @Override
    public List<IndustryDomain> getAllListed() {
        return queryByNamedQuery("IndustryDomain.getAllListed");
    }
}