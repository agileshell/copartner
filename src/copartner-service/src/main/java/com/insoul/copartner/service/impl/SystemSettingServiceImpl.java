package com.insoul.copartner.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insoul.copartner.dao.ISystemSettingDao;
import com.insoul.copartner.domain.SystemSetting;
import com.insoul.copartner.service.ISystemSettingService;

@Service
public class SystemSettingServiceImpl extends BaseServiceImpl implements ISystemSettingService {

    @Resource
    private ISystemSettingDao systemSettingDao;

    @Override
    public Map<String, String> getSettings(String settingGroup) {
        Map<String, String> systemconfig = new HashMap<String, String>();

        List<SystemSetting> settings = systemSettingDao.getSettings(settingGroup);
        for (SystemSetting setting : settings) {
            systemconfig.put(setting.getKey(), setting.getValue());
        }

        return systemconfig;
    }

}
