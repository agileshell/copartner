package com.insoul.copartner.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IQuestionDao;
import com.insoul.copartner.dao.criteria.QuestionCriteria;
import com.insoul.copartner.domain.Question;

@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question, Long>implements IQuestionDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Question> queryQuestion(QuestionCriteria criteria) {
        return generateQuery(criteria, false).getResultList();
    }

    @Override
    public Long countQuestion(QuestionCriteria criteria) {
        Long count = (Long) generateQuery(criteria, true).getSingleResult();

        return count;
    }

    private Query generateQuery(QuestionCriteria criteria, boolean isCount) {
        StringBuilder conditionStr = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != criteria.getStatus()) {
            conditionStr.append(" AND status IN :status");
            params.put("status", new HashSet<String>(Arrays.asList(criteria.getStatus())));
        }
        if (null != criteria.getCategoryId() && criteria.getCategoryId() > 0) {
            conditionStr.append(" AND categoryId = :categoryId");
            params.put("categoryId", criteria.getCategoryId());
        }
        if (StringUtils.isNotBlank(criteria.getKeyword())) {
            conditionStr.append(" AND title like :keyword");
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
        if (null != criteria.getTutorId()) {
            conditionStr.append(" AND tutorId = :tutorId");
            params.put("tutorId", criteria.getTutorId());
        }
        if (criteria.isOnlyOwner()) {
            if (null != criteria.getUserId()) {
                conditionStr.append(" AND userId = :userId");
                params.put("userId", criteria.getUserId());
            }
        } else {
            if (null != criteria.getUserId()) {
                conditionStr.append(" AND (userId = :userId OR permission = 1)");
                params.put("userId", criteria.getUserId());
            } else {
                conditionStr.append(" AND permission = 1");
            }
        }

        Query query = null;
        StringBuilder hql = new StringBuilder();
        if (isCount) {
            hql.append("SELECT COUNT(*) FROM Question WHERE 1=1").append(conditionStr);
            query = createQuery(hql.toString(), params);
        } else {
            hql.append("FROM Question WHERE 1=1").append(conditionStr).append(" ORDER BY created DESC");
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
