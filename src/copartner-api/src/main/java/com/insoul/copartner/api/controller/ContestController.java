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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IContestService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.ContestEntryListRequest;
import com.insoul.copartner.vo.request.ContestListRequest;
import com.insoul.copartner.vo.request.ContestRegisterRequest;
import com.insoul.copartner.vo.request.PaginationRequest;

@Controller
public class ContestController extends BaseController {

    @Resource
    private IContestService contestService;

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listContests(@Valid ContestListRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        return ResponseUtil.jsonSucceed(contestService.listContests(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/{contestId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getContest(@PathVariable Long contestId) throws CException {
        return ResponseUtil.jsonSucceed(contestService.getContest(contestId), HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/latest", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getlatestContest() throws CException {
        return ResponseUtil.jsonSucceed(contestService.getLatestContest(), HttpStatus.OK);
    }

    @RequestMapping(value = "/contestEntries", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listContestEntries(@Valid ContestEntryListRequest requestData,
            BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        return ResponseUtil.jsonSucceed(contestService.listContestEntries(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/contestEntry/{contestEntryId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getContestEntry(@PathVariable Long contestEntryId) throws CException {
        return ResponseUtil.jsonSucceed(contestService.getContestEntry(contestEntryId), HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/{contestId}/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> register(@PathVariable Long contestId,
            @Valid ContestRegisterRequest requestData, BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        contestService.register(contestId, requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/contestEntry/{contestEntryId}/vote", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> vote(@PathVariable Long contestEntryId, @RequestParam String comment)
            throws CException {
        contestService.vote(contestEntryId, comment);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/contestEntry/{contestEntryId}/votes", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listVotes(@PathVariable Long contestEntryId,
            @Valid PaginationRequest requestData, BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(contestService.listVoteInfo(contestEntryId, requestData), HttpStatus.OK);
    }
}
