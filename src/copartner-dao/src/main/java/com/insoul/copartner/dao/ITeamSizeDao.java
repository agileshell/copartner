package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.TeamSize;

public interface ITeamSizeDao extends IBaseDao<TeamSize, Long> {

    List<TeamSize> getAllListed();

}
