package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserPasswordResetDao;
import com.insoul.copartner.domain.UserPasswordReset;

@Repository
public class UserPasswordResetDaoImpl extends BaseDaoImpl<UserPasswordReset, Long> implements IUserPasswordResetDao {

    @Override
    public UserPasswordReset getUserPasswordReset(Long userId, String code) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("code", code);

        return queryOneByNamedQuery("UserPasswordReset.findUserPasswordReset", parameters);
    }

}
