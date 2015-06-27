package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

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
    public List<FriendVO> listFriends(Boolean isPassed) {
        List<FriendVO> friendVOs = new ArrayList<FriendVO>();

        Set<Long> friendIds = new HashSet<Long>();
        List<UserFriends> userFriends = userFriendsDao.findByUserIdAndStatus(getUserId(), isPassed);
        for (UserFriends userFriend : userFriends) {
            friendIds.add(userFriend.getId().getFriendId());
        }

        List<User> friends = userDao.getUserByIds(friendIds);
        for (User user : friends) {
            FriendVO friendVO = new FriendVO();
            friendVO.setFriendId(user.getId());
            friendVO.setName(user.getName());
            friendVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
            friendVO.setImId(user.getImId());

            friendVOs.add(friendVO);
        }

        return friendVOs;
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
        friendVO.setAvatar(CDNUtil.getFullPath(friend.getAvatar()));
        friendVO.setImId(friend.getImId());

        return friendVO;
    }
}