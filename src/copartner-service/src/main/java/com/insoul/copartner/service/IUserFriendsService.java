package com.insoul.copartner.service;

import java.util.List;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.FriendVO;

public interface IUserFriendsService {

    void addFriend(Long friendId) throws CException;

    List<FriendVO> listFriends(Boolean isPassed);

    FriendVO acceptFriend(Long friendId) throws CException;
}
