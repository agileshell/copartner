package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.ICourseDao;
import com.insoul.copartner.dao.criteria.CourseCriteria;
import com.insoul.copartner.domain.Course;

@Repository
public class CourseDaoImpl extends BaseDaoImpl<Course, Long> implements ICourseDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> queryCourse(CourseCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countCourse(CourseCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(CourseCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getStatus()) {
            conditionStr.append(" AND status IN :status");
            params.put("status", new HashSet<String>(Arrays.asList(criteria.getStatus())));
        }
        if (StringUtils.isNotBlank(criteria.getKeyword())) {
            conditionStr.append(" AND name like :keyword");
            params.put("keyword", "%" + criteria.getKeyword() + "%");
        }
        if (null != criteria.getFrom()) {
            conditionStr.append(" AND created > :from");
            params.put("from", criteria.getFrom());
        }
        if (null != criteria.getTo()) {
            conditionStr.append(" AND created < :to");
            params.put("to", criteria.getTo());
        }
        if (null != criteria.getIsFree()) {
            conditionStr.append(" AND isFree = :isFree");
            params.put("isFree", criteria.getIsFree());
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM Course WHERE 1=1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM Course WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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
