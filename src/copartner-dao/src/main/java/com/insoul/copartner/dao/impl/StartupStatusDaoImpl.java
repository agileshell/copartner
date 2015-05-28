package com.insoul.copartner.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IStartupStatusDao;
import com.insoul.copartner.domain.StartupStatus;

@Repository
public class StartupStatusDaoImpl extends BaseDaoImpl<StartupStatus, Long> implements IStartupStatusDao {

    @Override
    public List<StartupStatus> getAllListed() {
        return queryByNamedQuery("StartupStatus.getAllListed");
    }

}
