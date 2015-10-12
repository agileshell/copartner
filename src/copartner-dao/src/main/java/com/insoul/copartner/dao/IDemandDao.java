package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.DemandCriteria;
import com.insoul.copartner.domain.Demand;

public interface IDemandDao extends IBaseDao<Demand, Long> {

    List<Demand> queryDemand(DemandCriteria criteria);

    Long countDemand(DemandCriteria criteria);

    Demand getDemandByProjectId(Long projectId);

}
