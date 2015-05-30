package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IDemandLikersDao;
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

}
