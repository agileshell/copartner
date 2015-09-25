package com.insoul.copartner.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.TutorCriteria;
import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements IUserDao {

    public User getUserByEmail(String email) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email);

        return queryOneByNamedQuery("User.findByEmail", parameters);
    }

    public User getUserByMobile(String mobile) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("mobile", mobile);

        return queryOneByNamedQuery("User.findByMobile", parameters);
    }

    @Override
    public List<User> getUserByIds(Set<Long> userIds) {
        List<User> users = new ArrayList<User>();
        if (null != userIds && userIds.size() > 0) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userIds", userIds);

            users = queryByNamedQuery("User.findByIds", parameters);
        }

        return users;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> query(UserCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long count(UserCriteria criteria) {
        return (Long) generateQuery(criteria, true).getSingleResult();
    }

    @Override
    public long count() {
        return ((java.math.BigInteger) createNativeQuery("SELECT COUNT(1) FROM user", new HashMap<String, Object>())
                .getSingleResult()).longValue();
    }

    private Query generateQuery(UserCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getId() && criteria.getId() > 0L) {
            conditionStr.append(" AND id = :id");
            params.put("id", criteria.getId());
        }
        if (StringUtils.isNotBlank(criteria.getName())) {
            conditionStr.append(" AND name like :name");
            params.put("name", "%" + criteria.getName() + "%");
        }
        if (StringUtils.isNotBlank(criteria.getEmail())) {
            conditionStr.append(" AND email like :email");
            params.put("email", "%" + criteria.getEmail() + "%");
        }
        if (StringUtils.isNotBlank(criteria.getMobile())) {
            conditionStr.append(" AND mobile like :mobile");
            params.put("mobile", "%" + criteria.getMobile() + "%");
        }
        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM User WHERE 1 = 1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM User WHERE 1 = 1").append(conditionStr).append(" ORDER BY created DESC");
            query = createQuery(hql.toString(), params);
            if ((criteria.getLimit() != null) && (criteria.getLimit() != 0)) {
                query.setMaxResults(criteria.getLimit());
                if (criteria.getOffset() != null) {
                    query.setFirstResult(criteria.getOffset());
                }
            }
        }
        return query;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> queryTutor(TutorCriteria criteria) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getStatus() && criteria.getStatus().length > 0) {
            conditionStr.append(" AND status IN(:status)");
            params.put("status", Arrays.asList(criteria.getStatus()));
        }
        if (StringUtils.isNotBlank(criteria.getKeyword())) {
            conditionStr.append(" AND name LIKE :name");
            params.put("name", "%" + criteria.getKeyword() + "%");
        }
        if (null != criteria.getFrom()) {
            conditionStr.append(" AND created > :from");
            params.put("from", criteria.getFrom());
        }
        if (null != criteria.getTo()) {
            conditionStr.append(" AND created < :to");
            params.put("to", criteria.getTo());
        }

        Query query = null;
        StringBuilder hql = new StringBuilder("FROM User WHERE status = 'active' AND roleId = 3");
        hql.append(conditionStr).append(" ORDER BY created DESC");
        query = createQuery(hql.toString(), params);
        if ((criteria.getLimit() != null) && (criteria.getLimit() != 0)) {
            query.setMaxResults(criteria.getLimit());
            if (criteria.getOffset() != null) {
                query.setFirstResult(criteria.getOffset());
            }
        }

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUsers(String keyword) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append(" FROM User WHERE status = 'active' AND roleId != 3 AND name like :keyword OR email like :keyword OR mobile like :keyword");
        params.put("keyword", "%" + keyword + "%");
        Query query = createQuery(hql.toString(), params);

        return query.getResultList();
    }
}
