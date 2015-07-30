package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.TeamSizeCriteria;
import com.insoul.copartner.domain.TeamSize;

public interface ITeamSizeDao extends IBaseDao<TeamSize, Long> {

    List<TeamSize> getAllListed();

    List<TeamSize> query(TeamSizeCriteria criteria);
}