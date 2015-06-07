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
import com.insoul.copartner.service.IDemandService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.DemandAddRequest;
import com.insoul.copartner.vo.request.DemandCommentRequest;
import com.insoul.copartner.vo.request.DemandListRequest;
import com.insoul.copartner.vo.request.PaginationRequest;

@Controller
public class DemandController extends BaseController {

    @Resource
    private IDemandService demandService;

    @RequestMapping(value = "/user/{userId}/demands", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listDemand(@PathVariable Long userId,
            @Valid DemandListRequest requestData, BindingResult result) {
        requestData.setUserId(userId);

        return ResponseUtil.jsonSucceed(demandService.listDemands(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/demands", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listDemand(@Valid DemandListRequest requestData, BindingResult result)
            throws DataValidationException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(demandService.listDemands(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/demand/{demandId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getDemand(@PathVariable Long demandId) throws CException {
        return ResponseUtil.jsonSucceed(demandService.getDemand(demandId), HttpStatus.OK);
    }

    @RequestMapping(value = "/demand", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createDemand(@Valid DemandAddRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        demandService.createDemand(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/demand/{demandId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteDemand(@PathVariable Long demandId) throws CException {
        demandService.deleteDemand(demandId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/demand/{demandId}/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentDemand(@PathVariable Long demandId,
            @Valid DemandCommentRequest requestData, BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        demandService.commentDemand(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/demand/{demandId}/comments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listComments(@PathVariable Long demandId, PaginationRequest requestData)
            throws CException {
        if (null == demandId || demandId <= 0) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(demandService.listComments(demandId, requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/demand/{demandId}/like", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> likeDemand(@PathVariable Long demandId) throws CException {

        demandService.likeDemand(demandId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/demand/{demandId}/like", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> unlikeDemand(@PathVariable Long demandId) throws CException {

        demandService.unlikeDemand(demandId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

}
