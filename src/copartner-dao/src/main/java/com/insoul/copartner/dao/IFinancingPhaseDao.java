package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.FinancingPhase;

public interface IFinancingPhaseDao extends IBaseDao<FinancingPhase, Long> {

    List<FinancingPhase> getAllListed();

}
