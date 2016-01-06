package com.insoul.copartner.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import com.insoul.copartner.exception.UserNotFoundException;
import com.insoul.copartner.security.SecurityUtil;
import com.insoul.copartner.service.IUserService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.util.ValidationUtil;
import com.insoul.copartner.vo.LoginResponse;
import com.insoul.copartner.vo.UserDetailVO;
import com.insoul.copartner.vo.request.PasswordChangeRequest;
import com.insoul.copartner.vo.request.PasswordRestRequest;
import com.insoul.copartner.vo.request.SignInByThirdPartRequest;
import com.insoul.copartner.vo.request.UserAddRequest;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {

    @Resource
    private IUserService userService;

    @Resource
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/loginHandle")
    public ResponseEntity<String> loginHandle() throws CException {
        throw CExceptionFactory.getException(UserNotFoundException.class, ResponseCode.FORBIDDEN);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> register(@Valid UserAddRequest userAddRequest, BindingResult validResult,
            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws CException {
        if (validResult.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        UserDetailVO userDetailVO = userService.register(userAddRequest);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userId", userDetailVO.getUserId());
        result.put("roleId", userDetailVO.getRoleId());
        result.put("name", userDetailVO.getName());
        result.put("avatar", userDetailVO.getAvatar());
        result.put("imId", userDetailVO.getImId());

        UserDetails details = userDetailsService.loadUserByUsername(userAddRequest.getAccount());
        SecurityUtil.login(details, request, response, session);

        return ResponseUtil.jsonSucceed(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/password/retrieve", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> retrievePassword(
            @RequestParam(value = "account", required = false) String account) throws CException {
        if (ValidationUtil.isMobilePhoneNumber(account) || ValidationUtil.isEmail(account)) {
            userService.retrievePassword(account);

            return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
        } else {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> resetPassword(@Valid PasswordRestRequest request,
            BindingResult validResult) throws CException {
        if (validResult.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        userService.resetPassword(request.getAccount(), request.getCode(), request.getPassword());

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/password/change", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> changePassword(@Valid PasswordChangeRequest request,
            BindingResult validResult) throws CException {
        if (validResult.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        userService.changePassword(request.getOldPassword(), request.getPassword());

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/signin/{providerId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> signInAccount(@PathVariable("providerId") Integer providerId,
            @Valid SignInByThirdPartRequest signInBy3rdPartRequest, BindingResult validResult,
            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws CException {
        if (providerId > 3 || providerId < 1 || validResult.hasErrors() || signInBy3rdPartRequest.getUid().equalsIgnoreCase("undefined") ) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }
        signInBy3rdPartRequest.setProviderId(providerId);

        LoginResponse loginResponse = userService.loginUse3rdOauth(signInBy3rdPartRequest);
        String securityUserName = SecurityUtil.get3rdSecurityUserName(providerId, loginResponse.getUserId());
        UserDetails details = userDetailsService.loadUserByUsername(securityUserName);
        SecurityUtil.login(details, request, response, session);

        return ResponseUtil.jsonSucceed(loginResponse, HttpStatus.OK);
    }

}
