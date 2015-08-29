package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.domain.QuestionCategory;

public interface IQuestionCategoryDao extends IBaseDao<QuestionCategory, Long> {

    List<QuestionCategory> getAllListed();
}
