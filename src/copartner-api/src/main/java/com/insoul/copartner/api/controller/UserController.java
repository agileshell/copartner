package com.insoul.copartner.api.controller;

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
import com.insoul.copartner.service.IUserService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.UserProfileUpdateRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getProfileDetail() {

        return ResponseUtil.jsonSucceed(userService.getUserProfileDetail(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateProfile(@Valid UserProfileUpdateRequest profileUpdateRequest,
            BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        userService.updateProfile(profileUpdateRequest);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/profile", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getProfileDetail(@PathVariable Long userId) throws CException {

        return ResponseUtil.jsonSucceed(userService.getUserProfileDetail(userId), HttpStatus.OK);
    }

}
