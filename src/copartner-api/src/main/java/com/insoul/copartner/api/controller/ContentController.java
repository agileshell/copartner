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
import com.insoul.copartner.service.IContentService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.ContentListRequest;

@Controller
public class ContentController extends BaseController {

    @Resource
    private IContentService contentService;

    @RequestMapping(value = "/contents", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listContents(@Valid ContentListRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(contentService.listContents(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getContent(@PathVariable Long contentId) throws CException {
        return ResponseUtil.jsonSucceed(contentService.getContent(contentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/content/{contentId}/like", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> likeOrUnlikeContent(@PathVariable Long contentId) throws CException {
        contentService.likeOrUnlikeContent(contentId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }
}
