package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.DemandLikers;
import com.insoul.copartner.domain.DemandLikersId;

public interface IDemandLikersDao extends IBaseDao<DemandLikers, DemandLikersId> {

    DemandLikers findByIds(Long demandId, Long userId);

    List<DemandLikers> findByDemandId(Long demandId);

    List<DemandLikers> findByDemandIdsAndPagination(Set<Long> demandIds, PaginationCriteria pagination);
}
