package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserDeviceTokenDao;
import com.insoul.copartner.domain.UserDeviceToken;

@Repository
public class UserDeviceTokenDaoImpl extends BaseDaoImpl<UserDeviceToken, Long> implements IUserDeviceTokenDao {

    @Override
    public void deleteByUserId(Long userId) {
        getEM().createNamedQuery("UserDeviceToken.deletebyUserId").setParameter("userId", userId).executeUpdate();
    }

    @Override
    public UserDeviceToken getByUserId(Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);

        return queryOneByNamedQuery("UserDeviceToken.getByUserId", parameters);
    }

    @Override
    public UserDeviceToken getByDeviceTokenAndOs(String deviceOs, String deviceToken) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("deviceOs", deviceOs);
        parameters.put("deviceToken", deviceToken);

        return queryOneByNamedQuery("UserDeviceToken.getByDeviceTokenAndOs", parameters);
    }

}
