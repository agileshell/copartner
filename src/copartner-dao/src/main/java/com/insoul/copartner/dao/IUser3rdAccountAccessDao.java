package com.insoul.copartner.dao;

import com.insoul.copartner.domain.User3rdAccountAccess;

public interface IUser3rdAccountAccessDao extends IBaseDao<User3rdAccountAccess, Long> {

    User3rdAccountAccess getByProviderAndUid(Integer providerId, String uid);
}
