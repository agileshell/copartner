package com.insoul.copartner.api.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IQuestionService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.QuestionAddRequest;
import com.insoul.copartner.vo.request.QuestionListRequest;

@Controller
public class QuestionController extends BaseController {

    @Resource
    private IQuestionService questionService;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listQuestions(@Valid QuestionListRequest requestData,
            BindingResult result) throws DataValidationException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(questionService.listQuestions(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getQuestion(@PathVariable Long questionId) throws CException {
        return ResponseUtil.jsonSucceed(questionService.getQuestion(questionId), HttpStatus.OK);
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createQuestion(@Valid QuestionAddRequest requestData,
            BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        questionService.createQuestion(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/question/{questionId}/answer", method = RequestMethod.POST)
    @ResponseBody
    private ResponseEntity<Map<String, Object>> answer(@PathVariable Long questionId, String content) throws CException {
        if (questionId <= 0 || StringUtils.isBlank(content)) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        questionService.answer(questionId, content);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }
}
