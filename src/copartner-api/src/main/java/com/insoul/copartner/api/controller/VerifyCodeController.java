package com.insoul.copartner.api.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IVerifyCodeService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.CodeSendRequest;
import com.insoul.copartner.vo.request.CodeVerifyRequest;

@Controller
@RequestMapping(value = "/code")
public class VerifyCodeController extends BaseController {

    @Resource
    private IVerifyCodeService verifyCodeService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> sendCode(@Valid CodeSendRequest codeSendRequest, BindingResult validResult)
            throws CException {
        if (validResult.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        verifyCodeService.sendVerifyCode(codeSendRequest.getMobile(), codeSendRequest.getType());

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> verifyCode(@Valid CodeVerifyRequest codeVerifyRequest, BindingResult validResult)
            throws CException {
        if (validResult.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        verifyCodeService.verifyCode(codeVerifyRequest.getMobile(), codeVerifyRequest.getType(),
                codeVerifyRequest.getCode());

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }
}
