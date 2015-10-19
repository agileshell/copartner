package com.insoul.copartner.dao;

import com.insoul.copartner.domain.UserDeviceToken;

public interface IUserDeviceTokenDao extends IBaseDao<UserDeviceToken, Long> {

    void deleteByUserId(Long userId);

    UserDeviceToken getByUserId(Long userId);

    UserDeviceToken getByDeviceTokenAndOs(String deviceOs, String deviceToken);
}
