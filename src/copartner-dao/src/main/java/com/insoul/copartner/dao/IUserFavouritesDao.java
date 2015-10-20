package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.UserFavourites;

public interface IUserFavouritesDao extends IBaseDao<UserFavourites, Long> {

    UserFavourites getByUserIdAndEntity(Long userId, Long entityId, String entityType);

    List<UserFavourites> findByUserId(Long userId);
}
