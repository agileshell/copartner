package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.User;

public interface IUserDao extends IBaseDao<User, Long> {

    User getUserByEmail(String email);

    User getUserByMobile(String mobile);

    List<User> getUserByIds(Set<Long> userIds);

    List<User> query(UserCriteria criteria);
}
