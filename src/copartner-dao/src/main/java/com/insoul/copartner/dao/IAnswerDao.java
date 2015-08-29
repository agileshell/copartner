package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.Answer;

public interface IAnswerDao extends IBaseDao<Answer, Long> {

    List<Answer> findAnswers(Long questionId);

}
