package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.FinancingCriteria;
import com.insoul.copartner.domain.Financing;

public interface IFinancingDao extends IBaseDao<Financing, Long> {

    List<Financing> queryFinancing(FinancingCriteria criteria);

    Long countFinancing(FinancingCriteria criteria);

}
