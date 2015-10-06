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
import com.insoul.copartner.service.IFinancingService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.FinancingAddRequest;
import com.insoul.copartner.vo.request.FinancingListRequest;

@Controller
public class FinancingController extends BaseController {

    @Resource
    private IFinancingService financingService;

    @RequestMapping(value = "/user/{userId}/financings", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listDemand(@PathVariable Long userId,
            @Valid FinancingListRequest requestData, BindingResult result) {
        requestData.setUserId(userId);

        return ResponseUtil.jsonSucceed(financingService.listFinancings(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/financings", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listFinancing(@Valid FinancingListRequest requestData,
            BindingResult result) throws DataValidationException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(financingService.listFinancings(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/financing", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createFinancing(@Valid FinancingAddRequest requestData,
            BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        financingService.createFinancing(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/financing/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getNews(@PathVariable Long id) throws CException {
        return ResponseUtil.jsonSucceed(financingService.getFinancingDetail(id), HttpStatus.OK);
    }
}
