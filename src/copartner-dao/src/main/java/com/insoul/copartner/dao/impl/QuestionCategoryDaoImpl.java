package com.insoul.copartner.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IQuestionCategoryDao;
import com.insoul.copartner.domain.QuestionCategory;

@Repository
public class QuestionCategoryDaoImpl extends BaseDaoImpl<QuestionCategory, Long> implements IQuestionCategoryDao {

    @Override
    public List<QuestionCategory> getAllListed() {
        return queryByNamedQuery("QuestionCategory.getAllListed");
    }
}
