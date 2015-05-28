package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.StartupStatus;

public interface IStartupStatusDao extends IBaseDao<StartupStatus, Long> {
    List<StartupStatus> getAllListed();
}
