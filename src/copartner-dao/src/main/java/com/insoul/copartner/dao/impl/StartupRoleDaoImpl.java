package com.insoul.copartner.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.domain.StartupRole;

@Repository
public class StartupRoleDaoImpl extends BaseDaoImpl<StartupRole, Long> implements IStartupRoleDao {

    @Override
    public List<StartupRole> getAllListed() {
        return queryByNamedQuery("StartupRole.getAllListed");
    }
}