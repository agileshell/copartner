package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IDemandCommentsDao;
import com.insoul.copartner.dao.criteria.DemandCommentCriteria;
import com.insoul.copartner.domain.DemandComments;

@Repository
public class DemandCommentsDaoImpl extends BaseDaoImpl<DemandComments, Long> implements IDemandCommentsDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<DemandComments> queryComments(DemandCommentCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countComments(DemandCommentCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(DemandCommentCriteria criteria, boolean isCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("demandId", criteria.getDemandId());

        Query query = null;
        if (isCount) {
            String hql = "SELECT COUNT(*) FROM DemandComments WHERE demandId = :demandId AND status = 'active'";
            query = createQuery(hql, params);
        } else {
            String hql = "FROM DemandComments WHERE demandId = :demandId AND status = 'active' ORDER BY created DESC";
            query = createQuery(hql, params);
            if ((criteria.getLimit() != null) && (criteria.getLimit() != 0)) {
                query.setMaxResults(criteria.getLimit());
                if (criteria.getOffset() != null) {
                    query.setFirstResult(criteria.getOffset());
                }
            }
        }

        return query;
    }
}
