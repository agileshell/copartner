package com.insoul.copartner.api.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import com.insoul.copartner.service.IRequirementService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.PaginationRequest;
import com.insoul.copartner.vo.request.RequirementAddRequest;
import com.insoul.copartner.vo.request.RequirementCommentRequest;
import com.insoul.copartner.vo.request.RequirementListRequest;

@Controller
public class RequirementController extends BaseController {

    @Resource
    private IRequirementService requirementService;

    @RequestMapping(value = "/user/{userId}/requirements", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listRequirements(@PathVariable Long userId,
            @Valid RequirementListRequest requestData, BindingResult result) {
        requestData.setUserId(userId);

        return ResponseUtil.jsonSucceed(requirementService.listRequirements(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/requirements", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listRequirements(@Valid RequirementListRequest requestData,
            BindingResult result) throws DataValidationException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(requirementService.listRequirements(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/requirement/{requirementId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getRequirement(@PathVariable Long requirementId) throws CException {
        return ResponseUtil.jsonSucceed(requirementService.getRequirement(requirementId), HttpStatus.OK);
    }

    @RequestMapping(value = "/requirement", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createRequirement(@Valid RequirementAddRequest requestData,
            BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        requirementService.createRequirement(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/requirement/{requirementId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteRequirement(@PathVariable Long requirementId) throws CException {
        requirementService.deleteRequirement(requirementId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/requirement/{requirementId}/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentRequirement(@PathVariable Long requirementId,
            @Valid RequirementCommentRequest requestData, BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        requestData.setRequirementId(requirementId);
        requirementService.commentRequirement(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/requirement/{requirementId}/comments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listRequirementComments(@PathVariable Long requirementId,
            PaginationRequest requestData) throws CException {
        if (null == requirementId || requirementId <= 0) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(requirementService.listComments(requirementId, requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/requirement/{requirementId}/like", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> likeRequirement(@PathVariable Long requirementId) throws CException {

        requirementService.likeRequirement(requirementId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/requirement/{requirementId}/like", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> unlikeRequirement(@PathVariable Long requirementId) throws CException {

        requirementService.unlikeRequirement(requirementId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/requirements/refresh", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listRefreshInfo(@RequestParam String ids) throws DataValidationException {
        Set<Long> requirementIds = new HashSet<Long>();
        String strIds[] = ids.split(",");
        for (String id : strIds) {
            requirementIds.add(Long.valueOf(id));
        }

        return ResponseUtil.jsonSucceed(requirementService.listRefreshInfo(requirementIds), HttpStatus.OK);
    }
}
