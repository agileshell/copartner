package com.insoul.copartner.api.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

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
import com.insoul.copartner.service.IContestService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.ContestListRequest;

@Controller
public class ContestController extends BaseController {

    @Resource
    private IContestService contestService;

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listContestEntries(@Valid ContestListRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        return ResponseUtil.jsonSucceed(contestService.listContests(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getContent(@PathVariable Long contestId) throws CException {
        return ResponseUtil.jsonSucceed(contestService.getContest(contestId), HttpStatus.OK);
    }
}