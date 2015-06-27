package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.UserFriends;
import com.insoul.copartner.domain.UserFriendsId;

public interface IUserFriendsDao extends IBaseDao<UserFriends, UserFriendsId> {

    UserFriends findByIds(Long userId, Long friendId);

    List<UserFriends> findByUserIdAndStatus(Long userId, Boolean isPassed);

}
