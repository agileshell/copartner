package com.insoul.copartner.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.FriendVO;

public interface IUserFriendsService {

    void addFriend(Long friendId) throws CException;

    TreeMap<String, LinkedList<FriendVO>> listFriends(Boolean isPassed);

    FriendVO acceptFriend(Long friendId) throws CException;

    List<FriendVO> searchFriends(String keyword);
}
