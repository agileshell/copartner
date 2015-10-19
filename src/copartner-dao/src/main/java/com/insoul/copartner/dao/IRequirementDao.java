package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.RequirementCriteria;
import com.insoul.copartner.domain.Requirement;

public interface IRequirementDao extends IBaseDao<Requirement, Long> {

    List<Requirement> queryRequirement(RequirementCriteria criteria);

    Long countRequirement(RequirementCriteria criteria);

    List<Requirement> findByIds(Set<Long> ids);
}
