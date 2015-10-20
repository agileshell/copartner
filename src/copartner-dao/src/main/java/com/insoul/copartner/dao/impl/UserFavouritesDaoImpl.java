package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserFavouritesDao;
import com.insoul.copartner.dao.criteria.FavouriteCriteria;
import com.insoul.copartner.domain.UserFavourites;

@Repository
public class UserFavouritesDaoImpl extends BaseDaoImpl<UserFavourites, Long>implements IUserFavouritesDao {

    @Override
    public UserFavourites getByUserIdAndEntity(Long userId, Long entityId, String entityType) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userId", userId);
        parameters.put("entityId", entityId);
        parameters.put("entityType", entityType);

        return queryOneByNamedQuery("UserFavourites.getByUserIdAndEntity", parameters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserFavourites> findByUserId(FavouriteCriteria criteria) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        conditionStr.append(" AND userId = :userId");
        params.put("userId", criteria.getUserId());

        if (null != criteria.getFrom()) {
            conditionStr.append(" AND created > :from");
            params.put("from", criteria.getFrom());
        }
        if (null != criteria.getTo()) {
            conditionStr.append(" AND created < :to");
            params.put("to", criteria.getTo());
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        hql.append("FROM UserFavourites WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
        query = createQuery(hql.toString(), params);
        if ((criteria.getLimit() != null) && (criteria.getLimit() != 0)) {
            query.setMaxResults(criteria.getLimit());
            if (criteria.getOffset() != null) {
                query.setFirstResult(criteria.getOffset());
            }
        }

        return query.getResultList();
    }

}
