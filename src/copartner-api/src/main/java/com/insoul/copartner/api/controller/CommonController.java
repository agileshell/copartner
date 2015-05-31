package com.insoul.copartner.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.constant.SettingConstant;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.ISystemSettingService;
import com.insoul.copartner.service.IUtilityService;
import com.insoul.copartner.util.ResponseUtil;

@Controller
public class CommonController extends BaseController {

    @Resource
    private ISystemSettingService systemSettingService;

    @Resource
    private IUtilityService utilityService;

    @RequestMapping(value = "/serverTime", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getServerTime() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("serverTime", DateUtil.formatDate(new Date(), CommonConstant.DATE_FORMAT_LONG));

        return ResponseUtil.jsonSucceed(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/appVersion", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getAppVersion() {
        Map<String, String> result = systemSettingService.getSettings(SettingConstant.GROUP_TYPE_APP_INFO);

        return ResponseUtil.jsonSucceed(result, HttpStatus.OK);
    }

    @RequestMapping(value = "locations/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> listLocations(@PathVariable Long parentId) throws DataValidationException {
        if (parentId < 0) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(utilityService.listByParentId(parentId), HttpStatus.OK);
    }

    @RequestMapping(value = "industryDomains", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> listIndustryDomains() {

        return ResponseUtil.jsonSucceed(utilityService.listIndustryDomains(), HttpStatus.OK);
    }

    @RequestMapping(value = "startupRoles", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> listStartupRoles() {

        return ResponseUtil.jsonSucceed(utilityService.listStartupRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "startupStatus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> listStartupStatus() {

        return ResponseUtil.jsonSucceed(utilityService.listStartupStatus(), HttpStatus.OK);
    }

    @RequestMapping(value = "projectPhases", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> listProjectPhases() {

        return ResponseUtil.jsonSucceed(utilityService.listProjectPhases(), HttpStatus.OK);
    }

    @RequestMapping(value = "feedback", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addFeedback(String text) throws DataValidationException {
        if (!StringUtils.isNotBlank(text)) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        utilityService.feedback(text);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }
}
