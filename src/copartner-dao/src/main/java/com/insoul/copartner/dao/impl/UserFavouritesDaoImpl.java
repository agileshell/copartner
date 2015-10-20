package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserFavouritesDao;
import com.insoul.copartner.domain.UserFavourites;

@Repository
public class UserFavouritesDaoImpl extends BaseDaoImpl<UserFavourites, Long> implements IUserFavouritesDao {

    @Override
    public UserFavourites getByUserIdAndEntity(Long userId, Long entityId, String entityType) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("entityId", entityId);
        parameters.put("entityType", entityType);

        return queryOneByNamedQuery("UserFavourites.getByUserIdAndEntity", parameters);
    }

    @Override
    public List<UserFavourites> findByUserId(Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);

        return queryByNamedQuery("UserFavourites.findByUserId", parameters);
    }

}
