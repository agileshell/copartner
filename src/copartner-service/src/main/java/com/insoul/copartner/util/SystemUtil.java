package com.insoul.copartner.util;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insoul.copartner.constant.SettingConstant;
import com.insoul.copartner.service.ISystemSettingService;

@Component
public class SystemUtil {

    @Autowired
    private ISystemSettingService settingService;

    private static SystemUtil systemUtil;

    @PostConstruct
    public void init() {
        systemUtil = this;
        systemUtil.settingService = settingService;
    }

    public static Map<String, String> getSettings(String settingGroup) {
        return systemUtil.settingService.getSettings(settingGroup);
    }

    public static String getGeneralSetting(String key) {
        Map<String, String> settings = getSettings(SettingConstant.GROUP_TYPE_GENERAL);

        return settings.containsKey(key) ? settings.get(key) : "";
    }
}
