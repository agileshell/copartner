package com.insoul.copartner.service;

import java.util.List;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.QuestionCategoryVO;
import com.insoul.copartner.vo.QuestionDetailVO;
import com.insoul.copartner.vo.QuestionVO;
import com.insoul.copartner.vo.request.QuestionAddRequest;
import com.insoul.copartner.vo.request.QuestionListRequest;

public interface IQuestionService {

    Pagination<QuestionVO> listQuestions(QuestionListRequest requestData);

    QuestionDetailVO getQuestion(Long questionId) throws CException;

    void createQuestion(QuestionAddRequest requestData) throws CException;

    void answer(Long questionId, String content) throws CException;

    List<QuestionCategoryVO> listQuestionCategories();
}
