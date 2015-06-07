package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IDemandLikersDao;
import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.DemandLikers;
import com.insoul.copartner.domain.DemandLikersId;

@Repository
public class DemandLikersDaoImpl extends BaseDaoImpl<DemandLikers, DemandLikersId> implements IDemandLikersDao {

    @Override
    public DemandLikers findByIds(Long demandId, Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("demandId", demandId);

        return queryOneByNamedQuery("DemandLikers.getByIds", parameters);
    }

    @Override
    public List<DemandLikers> findByDemandId(Long demandId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("demandId", demandId);

        return queryByNamedQuery("DemandLikers.getByDemandId", parameters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DemandLikers> findByDemandIdsAndPagination(Set<Long> demandIds, PaginationCriteria pagination) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("demandIds", demandIds);

        Query query = createQuery("FROM DemandLikers WHERE id.demandId IN :demandIds ORDER BY created DESC", parameters);
        if ((pagination.getLimit() != null) && (pagination.getLimit() != 0)) {
            query.setMaxResults(pagination.getLimit());
            if (pagination.getOffset() != null) {
                query.setFirstResult(pagination.getOffset());
            }
        }

        return query.getResultList();
    }
}
