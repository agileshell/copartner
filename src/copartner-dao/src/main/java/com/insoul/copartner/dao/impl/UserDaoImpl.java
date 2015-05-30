package com.insoul.copartner.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public List<User> getUserByIds(Set<Long> userIds) {
        List<User> users = new ArrayList<User>();
        if (null != userIds && userIds.size() > 0) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userIds", userIds);

            users = queryByNamedQuery("User.findByIds", parameters);
        }

        return users;
    }

}
