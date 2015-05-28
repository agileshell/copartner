package com.insoul.copartner.dao;

import com.insoul.copartner.domain.User;

public interface IUserDao extends IBaseDao<User, Long> {

    User getUserByEmail(String email);

    User getUserByMobile(String mobile);
}
