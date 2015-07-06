package com.insoul.copartner.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long>implements IUserDao {

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
}
