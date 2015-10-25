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
import com.insoul.copartner.service.CampaignService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.CampaignListRequest;

@Controller
public class CampaignController extends BaseController {

    @Resource
    private CampaignService campaignService;

    @RequestMapping(value = "/campaigns", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listContents(@Valid CampaignListRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(campaignService.listCampaigns(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/campaign/{campaignId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getContent(@PathVariable Long campaignId) throws CException {
        return ResponseUtil.jsonSucceed(campaignService.getDetail(campaignId), HttpStatus.OK);
    }
}
