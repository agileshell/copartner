package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserFriendsDao;
import com.insoul.copartner.domain.UserFriends;
import com.insoul.copartner.domain.UserFriendsId;

@Repository
public class UserFriendsDaoImpl extends BaseDaoImpl<UserFriends, UserFriendsId> implements IUserFriendsDao {

    @Override
    public UserFriends findByIds(Long userId, Long friendId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("friendId", friendId);

        return queryOneByNamedQuery("UserFriends.getByIds", parameters);
    }

    @Override
    public List<UserFriends> findByUserIdAndStatus(Long userId, Boolean isPassed) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("isPassed", isPassed);

        return queryByNamedQuery("UserFriends.getByUserId", parameters);
    }
}
