package com.insoul.copartner.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.dao.IUserDeviceTokenDao;
import com.insoul.copartner.domain.UserDeviceToken;
import com.insoul.copartner.service.IDeviceService;

@Service
public class DeviceServiceImpl extends BaseServiceImpl implements IDeviceService {

    @Resource
    private IUserDeviceTokenDao userDeviceTokenDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void addUserDeviceToken(String deviceOS, String deviceToken) {
        Date now = new Date();

        UserDeviceToken userDeviceToken = userDeviceTokenDao.getByDeviceTokenAndOs(deviceOS, deviceToken);
        if (null == userDeviceToken) {
            userDeviceToken = new UserDeviceToken();
            userDeviceToken.setUserId(getUserId());
            userDeviceToken.setDeviceOs(deviceOS);
            userDeviceToken.setDeviceToken(deviceToken);
            userDeviceToken.setCreated(now);

            userDeviceTokenDao.save(userDeviceToken);
        } else {
            userDeviceToken.setUserId(getUserId());
            userDeviceToken.setUpdated(now);
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void deleteUserDeviceToken() {
        userDeviceTokenDao.deleteByUserId(getUserId());
    }

}
