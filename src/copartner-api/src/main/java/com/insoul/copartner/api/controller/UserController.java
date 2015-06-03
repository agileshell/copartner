package com.insoul.copartner.api.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IUserService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.ResumeRequest;
import com.insoul.copartner.vo.request.UserProfileUpdateRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getProfileDetail() {

        return ResponseUtil.jsonSucceed(userService.getUserProfileDetail(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateProfile(@Valid UserProfileUpdateRequest profileUpdateRequest,
            BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        userService.updateProfile(profileUpdateRequest);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/profile", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getProfileDetail(@PathVariable Long userId) throws CException {

        return ResponseUtil.jsonSucceed(userService.getUserProfileDetail(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/educationResumes", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listEducationResume() {

        return ResponseUtil.jsonSucceed(userService.listUserEducationResume(), HttpStatus.OK);
    }

    @RequestMapping(value = "/educationResume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateEducationResume(@RequestParam String resumes)
            throws DataValidationException {
        ResumeRequest[] array = null;
        try {
            array = jacksonObjectMapper.readValue(resumes, ResumeRequest[].class);
        } catch (Exception e) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        userService.updateEducationResume(Arrays.asList(array));

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/workResumes", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listWorkResume() {

        return ResponseUtil.jsonSucceed(userService.listUserWorkResume(), HttpStatus.OK);
    }

    @RequestMapping(value = "/workResume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateWorkResume(@RequestParam String resumes)
            throws DataValidationException {
        ResumeRequest[] array = null;
        try {
            array = jacksonObjectMapper.readValue(resumes, ResumeRequest[].class);
        } catch (Exception e) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        userService.updateWorkResume(Arrays.asList(array));

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }
}
