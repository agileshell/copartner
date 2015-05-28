package com.insoul.copartner.util;

import net.sf.json.JSONObject;

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

    public static ResponseEntity<String> jsonSucceed(Object object, HttpStatus statusCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", CommonConstant.APPLICATION_JSON);

        JSONObject jo = jsonSucceed(object);

        return new ResponseEntity<String>(jo.toString(), headers, statusCode);
    }

    public static JSONObject jsonSucceed(Object object) {
        JSONObject jo = new JSONObject();
        jo.accumulate(RESPONSE_BODY, object);
        jo.accumulate(RESPONSE_STATUS, ResponseStatus.SUCCEED.toString());

        return jo;
    }

    public static ResponseEntity<String> jsonFailed(String errorMessage, HttpStatus statusCode) {
        return jsonFailed(errorMessage, ResponseCode.SERVER_ERROR, statusCode);
    }

    public static ResponseEntity<String> jsonFailed(Object errorMessage, ResponseCode code, HttpStatus statusCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", CommonConstant.APPLICATION_JSON);

        JSONObject jo = jsonFailed(errorMessage, code);

        return new ResponseEntity<String>(jo.toString(), headers, statusCode);
    }

    public static JSONObject jsonFailed(Object errorMessage, ResponseCode code) {
        JSONObject errorObj = new JSONObject();
        errorObj.accumulate(RESPONSE_ERROR_CODE, code.getValue());
        errorObj.accumulate(RESPONSE_ERROR_MSG, errorMessage);

        JSONObject error = new JSONObject();
        error.accumulate(RESPONSE_ERROR, errorObj);

        JSONObject body = new JSONObject();
        body.accumulate(RESPONSE_BODY, error);
        body.accumulate(RESPONSE_STATUS, ResponseStatus.FAILED.toString());

        return body;
    }
}
