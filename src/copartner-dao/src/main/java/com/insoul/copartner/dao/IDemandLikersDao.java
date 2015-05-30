package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.DemandLikers;
import com.insoul.copartner.domain.DemandLikersId;

public interface IDemandLikersDao extends IBaseDao<DemandLikers, DemandLikersId> {

    DemandLikers findByIds(Long demandId, Long userId);

    List<DemandLikers> findByDemandId(Long demandId);
}
