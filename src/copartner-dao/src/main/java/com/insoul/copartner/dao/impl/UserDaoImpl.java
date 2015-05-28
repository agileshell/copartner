package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements IUserDao {

    public User getUserByEmail(String email) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email);

        return queryOneByNamedQuery("User.findByEmail", parameters);
    }

    public User getUserByMobile(String mobile) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("mobile", mobile);

        return queryOneByNamedQuery("User.findByMobile", parameters);
    }

}
