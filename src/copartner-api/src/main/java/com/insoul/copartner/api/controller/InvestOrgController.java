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
import com.insoul.copartner.service.IInvestOrgService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.InvestOrgListRequest;

@Controller
public class InvestOrgController extends BaseController {

    @Resource
    private IInvestOrgService investOrgService;

    @RequestMapping(value = "/investorgs", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listContents(@Valid InvestOrgListRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(investOrgService.listInvestOrgs(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/investorg/{investorgId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getContent(@PathVariable Long investorgId) throws CException {
        return ResponseUtil.jsonSucceed(investOrgService.getInvestOrg(investorgId), HttpStatus.OK);
    }
}
