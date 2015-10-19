package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUser3rdAccountAccessDao;
import com.insoul.copartner.domain.User3rdAccountAccess;

@Repository
public class User3rdAccountAccessDaoImpl extends BaseDaoImpl<User3rdAccountAccess, Long> implements
        IUser3rdAccountAccessDao {

    @Override
    public User3rdAccountAccess getByProviderAndUid(Integer providerId, String uid) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("providerId", providerId);
        parameters.put("uid", uid);

        return queryOneByNamedQuery("User3rdAccountAccess.getByProviderAnduId", parameters);
    }

}
