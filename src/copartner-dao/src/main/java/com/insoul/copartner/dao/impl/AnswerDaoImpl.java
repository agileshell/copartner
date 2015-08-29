package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IAnswerDao;
import com.insoul.copartner.domain.Answer;

@Repository
public class AnswerDaoImpl extends BaseDaoImpl<Answer, Long> implements IAnswerDao {

    @Override
    public List<Answer> findAnswers(Long questionId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("questionId", questionId);

        return queryByNamedQuery("Answer.findByQuestionId", parameters);
    }

}
