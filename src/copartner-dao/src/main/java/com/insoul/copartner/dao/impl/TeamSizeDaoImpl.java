package com.insoul.copartner.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.domain.TeamSize;

@Repository
public class TeamSizeDaoImpl extends BaseDaoImpl<TeamSize, Long> implements ITeamSizeDao {

    @Override
    public List<TeamSize> getAllListed() {
        return queryByNamedQuery("TeamSize.getAllListed");
    }
}
