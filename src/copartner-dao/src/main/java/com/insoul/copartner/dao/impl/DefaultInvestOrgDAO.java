package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IInvestOrgDAO;
import com.insoul.copartner.dao.criteria.InvestOrgCriteria;
import com.insoul.copartner.domain.InvestOrg;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 上午11:32:11
 */
@Repository
public class DefaultInvestOrgDAO extends BaseDaoImpl<InvestOrg, Long> implements IInvestOrgDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<InvestOrg> queryInvestOrg(InvestOrgCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countInvestOrg(InvestOrgCriteria criteria) {
        return (Long) generateQuery(criteria, true).getSingleResult();
    }
    
    private Query generateQuery(InvestOrgCriteria criteria, boolean count) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNoneBlank(criteria.getName())) {
            conditionStr.append(" AND name LIKE :name ");
            params.put("name", "%" + criteria.getName() + "%");
        }
        if (StringUtils.isNoneBlank(criteria.getSpecials())) {
            conditionStr.append(" AND specials LIKE :specials ");
            params.put("specials", "%" + criteria.getSpecials() + "%");
        }
        if (StringUtils.isNoneBlank(criteria.getHardware())) {
            conditionStr.append(" AND hardware LIKE :hardware ");
            params.put("hardware", "%" + criteria.getHardware() + "%");
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (count) {
            hql.append("SELECT COUNT(*) FROM InvestOrganization WHERE 1 = 1 ").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM InvestOrganization WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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