package com.insoul.copartner.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IFinancingPhaseDao;
import com.insoul.copartner.domain.FinancingPhase;


@Repository
public class FinancingPhaseDaoImpl extends BaseDaoImpl<FinancingPhase, Long> implements IFinancingPhaseDao {

    @Override
    public List<FinancingPhase> getAllListed() {
        return queryByNamedQuery("FinancingPhase.getAllListed");
    }

}
