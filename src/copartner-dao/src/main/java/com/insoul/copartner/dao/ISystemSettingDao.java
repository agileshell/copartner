package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.SystemSetting;

public interface ISystemSettingDao extends IBaseDao<SystemSetting, Long> {

    List<SystemSetting> getSettings(String group);
}
