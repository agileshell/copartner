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
import com.insoul.copartner.service.IPioneerParkService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.PoineerParkListRequest;

@Controller
public class PoineerParkController extends BaseController {

    @Resource
    private IPioneerParkService pioneerParkService;

    @RequestMapping(value = "/pioneerparks", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listContents(@Valid PoineerParkListRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(pioneerParkService.listPioneerParks(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/pioneerpark/{pioneerparkId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getContent(@PathVariable Long pioneerparkId) throws CException {
        return ResponseUtil.jsonSucceed(pioneerParkService.getPioneerPark(pioneerparkId), HttpStatus.OK);
    }
}
