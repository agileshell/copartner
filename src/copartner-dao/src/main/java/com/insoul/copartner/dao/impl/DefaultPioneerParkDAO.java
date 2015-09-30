package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IPioneerParkDAO;
import com.insoul.copartner.dao.criteria.PioneerParkCriteria;
import com.insoul.copartner.domain.PioneerPark;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 上午11:30:45
 */
@Repository
public class DefaultPioneerParkDAO extends BaseDaoImpl<PioneerPark, Long> implements IPioneerParkDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<PioneerPark> queryPioneerPark(PioneerParkCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countPioneerPark(PioneerParkCriteria criteria) {
        return (Long) generateQuery(criteria, true).getSingleResult();
    }
    
    private Query generateQuery(PioneerParkCriteria criteria, boolean count) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNoneBlank(criteria.getName())) {
            conditionStr.append(" AND name LIKE :name ");
            params.put("name", "%" + criteria.getName() + "%");
        }
        if (StringUtils.isNoneBlank(criteria.getProvince())) {
            conditionStr.append(" AND province LIKE :province ");
            params.put("province", "%" + criteria.getProvince() + "%");
        }
        if (StringUtils.isNoneBlank(criteria.getCity())) {
            conditionStr.append(" AND city LIKE :city ");
            params.put("city", "%" + criteria.getCity() + "%");
        }
        if (StringUtils.isNoneBlank(criteria.getArea())) {
            conditionStr.append(" AND area LIKE :area ");
            params.put("area", "%" + criteria.getArea() + "%");
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
        StringBuilder hql = new StringBuilder();
        if (count) {
            hql.append("SELECT COUNT(*) FROM PioneerPark WHERE 1 = 1 ").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM PioneerPark WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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