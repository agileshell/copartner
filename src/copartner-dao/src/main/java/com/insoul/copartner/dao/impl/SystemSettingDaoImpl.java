package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.ISystemSettingDao;
import com.insoul.copartner.domain.SystemSetting;

@Repository
public class SystemSettingDaoImpl extends BaseDaoImpl<SystemSetting, Long> implements ISystemSettingDao {

    @Override
    public List<SystemSetting> getSettings(String group) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("group", group);

        return queryByNamedQuery("SystemSetting.getByGroup", parameters);
    }

}
