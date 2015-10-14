package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IRequirementLikersDao;
import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.RequirementLikers;
import com.insoul.copartner.domain.RequirementLikersId;

@Repository
public class RequirementLikersDaoImpl extends BaseDaoImpl<RequirementLikers, RequirementLikersId> implements
        IRequirementLikersDao {

    @Override
    public RequirementLikers findByIds(Long requirementId, Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("requirementId", requirementId);

        return queryOneByNamedQuery("RequirementLikers.getByIds", parameters);
    }

    @Override
    public List<RequirementLikers> findByRequirementId(Long requirementId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("requirementId", requirementId);

        return queryByNamedQuery("RequirementLikers.getByRequirementId", parameters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RequirementLikers> findByRequirementIdsAndPagination(Set<Long> requirementIds,
            PaginationCriteria pagination) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("requirementIds", requirementIds);

        Query query =
                createQuery("FROM RequirementLikers WHERE id.requirementId IN (:requirementIds) ORDER BY created DESC",
                        parameters);
        if ((pagination.getLimit() != null) && (pagination.getLimit() != 0)) {
            query.setMaxResults(pagination.getLimit());
            if (pagination.getOffset() != null) {
                query.setFirstResult(pagination.getOffset());
            }
        }

        return query.getResultList();
    }

}
