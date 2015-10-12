package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.RequirementLikers;
import com.insoul.copartner.domain.RequirementLikersId;

public interface IRequirementLikersDao extends IBaseDao<RequirementLikers, RequirementLikersId> {

    RequirementLikers findByIds(Long requirementId, Long userId);

    List<RequirementLikers> findByRequirementId(Long requirementId);

    List<RequirementLikers> findByRequirementIdsAndPagination(Set<Long> requirementIds, PaginationCriteria pagination);
}
