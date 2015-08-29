package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.QuestionCriteria;
import com.insoul.copartner.domain.Question;

public interface IQuestionDao extends IBaseDao<Question, Long> {

    List<Question> queryQuestion(QuestionCriteria criteria);

    Long countQuestion(QuestionCriteria criteria);
}
