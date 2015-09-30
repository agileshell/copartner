package com.insoul.copartner.api.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.exception.UserNotFoundException;
import com.insoul.copartner.security.ContextUtil;
import com.insoul.copartner.util.ExceptionMessageUtil;
import com.insoul.copartner.util.ResponseUtil;

public abstract class BaseController {
    
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

    protected final ObjectMapper jacksonObjectMapper = new ObjectMapper();

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        PropertyEditor editor = new CustomDateEditor(new SimpleDateFormat(CommonConstant.DATE_FORMAT_LONG), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleException(Throwable ex) {
        Object errorMessage = null;
        ResponseCode code = ResponseCode.SERVER_ERROR;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof CException) {
            code = ((CException) ex).getCode() != null ? ((CException) ex).getCode() : code;
            errorMessage = ExceptionMessageUtil.getExceptionMessage(code.getValue());

            if (code.getValue() != ResponseCode.SERVER_ERROR.getValue()) {
                if (ex instanceof DataValidationException) {
                    httpStatus = HttpStatus.BAD_REQUEST;
                } else if (ex instanceof UserNotFoundException) {
                    httpStatus = HttpStatus.FORBIDDEN;
                } else {
                    httpStatus = HttpStatus.OK;
                }
            }
        } else {
            errorMessage = "server error";
        }

        return ResponseUtil.jsonFailed(errorMessage, code, httpStatus);
    }

    protected long getCurrentUserId() {
        return ContextUtil.getUserId();
    }
}
