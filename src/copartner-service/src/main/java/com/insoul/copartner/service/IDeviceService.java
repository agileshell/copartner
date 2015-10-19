package com.insoul.copartner.service;

public interface IDeviceService {

    void addUserDeviceToken(String deviceOS, String deviceToken);

    void deleteUserDeviceToken();
}
