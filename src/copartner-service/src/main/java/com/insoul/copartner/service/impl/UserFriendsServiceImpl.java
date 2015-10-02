package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.IUserFriendsDao;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.domain.UserFriends;
import com.insoul.copartner.domain.UserFriendsId;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IUserFriendsService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.Chinese2Spell;
import com.insoul.copartner.vo.FriendVO;

@Service
public class UserFriendsServiceImpl extends BaseServiceImpl implements IUserFriendsService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserFriendsDao userFriendsDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void addFriend(Long friendId) throws CException {
        User friend = userDao.get(friendId);
        if (null == friend) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.USER_NOT_EXIST);
        }

        long currentUserId = getUserId();
        UserFriends userFriends = userFriendsDao.findByIds(currentUserId, friendId);
        if (null == userFriends) {
            userFriends = new UserFriends();
            UserFriendsId id = new UserFriendsId(currentUserId, friendId);
            userFriends.setId(id);
            userFriends.setCreated(new Date());

            userFriendsDao.save(userFriends);
        }

    }

    @Override
    public LinkedHashMap<String, LinkedList<FriendVO>> listFriends(Boolean isPassed) {
        Set<Long> friendIds = new HashSet<Long>();
        List<UserFriends> userFriends = userFriendsDao.findByUserIdAndStatus(getUserId(), isPassed);
        for (UserFriends userFriend : userFriends) {
            friendIds.add(userFriend.getId().getFriendId());
        }
        List<User> friends = userDao.getUserByIds(friendIds);
        LinkedHashMap<String, LinkedList<FriendVO>> friendColl = new LinkedHashMap<String, LinkedList<FriendVO>>();
        for (User user : friends) {
            String abbr = Chinese2Spell.converterToFirstSpell(user.getName());
            String group = StringUtils.upperCase(StringUtils.substring(abbr, 0, 1));
            FriendVO friendVO = new FriendVO();
            friendVO.setFriendId(user.getId());
            friendVO.setName(user.getName());
            friendVO.setAbbr(abbr);
            friendVO.setPinyin(Chinese2Spell.converterToSpell(user.getName()));
            friendVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
            friendVO.setImId(user.getImId());
            LinkedList<FriendVO> farr = friendColl.get(group);
            if (farr == null) {
                farr = new LinkedList<FriendVO>();
            }
            farr.add(friendVO);
            friendColl.put(group, farr);
        }
        return friendColl;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public FriendVO acceptFriend(Long friendId) throws CException {
        User friend = userDao.get(friendId);
        if (null == friend) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.USER_NOT_EXIST);
        }

        long currentUserId = getUserId();
        UserFriends userFriends = userFriendsDao.findByIds(currentUserId, friendId);
        if (null != userFriends) {
            userFriends.setIsPassed(true);
            userFriendsDao.update(userFriends);
        }

        FriendVO friendVO = new FriendVO();
        friendVO.setFriendId(friend.getId());
        friendVO.setName(friend.getName());
        friendVO.setAbbr(Chinese2Spell.converterToFirstSpell(friend.getName()));
        friendVO.setPinyin(Chinese2Spell.converterToSpell(friend.getName()));
        friendVO.setAvatar(CDNUtil.getFullPath(friend.getAvatar()));
        friendVO.setImId(friend.getImId());

        return friendVO;
    }

    @Override
    public List<FriendVO> searchFriends(String keyword) {
        List<FriendVO> friendVOs = new ArrayList<FriendVO>();

        Set<Long> friendIds = new HashSet<Long>();
        List<UserFriends> userFriends = userFriendsDao.findByUserIdAndStatus(getUserId(), true);
        for (UserFriends userFriend : userFriends) {
            friendIds.add(userFriend.getId().getFriendId());
        }

        List<User> users = userDao.findUsers(keyword);
        for (User user : users) {
            if (!userFriends.contains(user.getId())) {
                FriendVO friendVO = new FriendVO();
                friendVO.setFriendId(user.getId());
                friendVO.setName(user.getName());
                friendVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
                friendVO.setImId(user.getImId());

                friendVOs.add(friendVO);
            }
        }

        return friendVOs;
    }
}
