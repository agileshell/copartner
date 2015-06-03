package com.insoul.copartner.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.constant.ResponseStatus;

public class ResponseUtil {


    public static final String RESPONSE_BODY = "body";

    public static final String RESPONSE_STATUS = "status";

    public static final String RESPONSE_ERROR = "error";

    public static final String RESPONSE_ERROR_CODE = "code";

    public static final String RESPONSE_ERROR_MSG = "msg";

    public static ResponseEntity<Map<String, Object>> jsonSucceed(Object object, HttpStatus statusCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", CommonConstant.APPLICATION_JSON);

        Map<String, Object> map = jsonSucceed(object);

        return new ResponseEntity<Map<String, Object>>(map, headers, statusCode);
    }

    public static Map<String, Object> jsonSucceed(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(RESPONSE_BODY, object);
        map.put(RESPONSE_STATUS, ResponseStatus.SUCCEED.toString());

        return map;
    }

    public static ResponseEntity<Map<String, Object>> jsonFailed(String errorMessage, HttpStatus statusCode) {
        return jsonFailed(errorMessage, ResponseCode.SERVER_ERROR, statusCode);
    }

    public static ResponseEntity<Map<String, Object>> jsonFailed(Object errorMessage, ResponseCode code,
            HttpStatus statusCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", CommonConstant.APPLICATION_JSON);

        Map<String, Object> map = jsonFailed(errorMessage, code);

        return new ResponseEntity<Map<String, Object>>(map, headers, statusCode);
    }

    public static Map<String, Object> jsonFailed(Object errorMessage, ResponseCode code) {
        Map<String, Object> errorObj = new HashMap<String, Object>();
        errorObj.put(RESPONSE_ERROR_CODE, code.getValue());
        errorObj.put(RESPONSE_ERROR_MSG, errorMessage);

        Map<String, Object> error = new HashMap<String, Object>();
        error.put(RESPONSE_ERROR, errorObj);

        Map<String, Object> body = new HashMap<String, Object>();
        body.put(RESPONSE_BODY, error);
        body.put(RESPONSE_STATUS, ResponseStatus.FAILED.toString());

        return body;
    }
}
