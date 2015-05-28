package com.insoul.copartner.service;

import java.util.Map;

public interface ISystemSettingService {
    Map<String, String> getSettings(String settingGroup);
}
