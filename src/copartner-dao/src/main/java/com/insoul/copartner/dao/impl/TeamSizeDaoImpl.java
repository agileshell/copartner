package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.dao.criteria.TeamSizeCriteria;
import com.insoul.copartner.domain.TeamSize;

@Repository
public class TeamSizeDaoImpl extends BaseDaoImpl<TeamSize, Long> implements ITeamSizeDao {

    @Override
    public List<TeamSize> getAllListed() {
        return queryByNamedQuery("TeamSize.getAllListed");
    }
    
    private Query generateQuery(TeamSizeCriteria criteria, boolean isCount) {
		StringBuilder conditionStr = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != criteria.getId() && criteria.getId() > 0L) {
			conditionStr.append(" AND id = :id ");
			params.put("id", criteria.getId());
		}
		if (StringUtils.isNotBlank(criteria.getName())) {
			conditionStr.append(" AND name like :name ");
			params.put("name", "%" + criteria.getName() + "%");
		}
		if (criteria.getListed() != null) {
			conditionStr.append(" AND isListed = :isListed ");
			params.put("isListed", criteria.getListed());
		}
		Query query = null;
		StringBuilder hql = new StringBuilder();
		if (isCount) {
			hql.append("SELECT COUNT(*) FROM TeamSize WHERE 1 = 1").append(conditionStr);
			query = createQuery(hql.toString(), params);
		} else {
			hql.append("FROM TeamSize WHERE 1 = 1").append(conditionStr).append(" ORDER BY created DESC");
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
	public List<TeamSize> query(TeamSizeCriteria criteria) {
		return generateQuery(criteria, false).getResultList();
	}
}
