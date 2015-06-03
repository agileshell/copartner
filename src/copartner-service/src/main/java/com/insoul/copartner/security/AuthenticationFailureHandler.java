package com.insoul.copartner.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.util.ExceptionMessageUtil;
import com.insoul.copartner.util.JsonUtil;
import com.insoul.copartner.util.ResponseUtil;

public class AuthenticationFailureHandler implements
        org.springframework.security.web.authentication.AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(CommonConstant.APPLICATION_JSON);
        PrintWriter out = null;
        try {
            out = response.getWriter();

            int code = ResponseCode.ACCOUNT_OR_PASSWORD_INCORRECT.getValue();
            Map<String, Object> json =
                    ResponseUtil.jsonFailed(ExceptionMessageUtil.getExceptionMessage(code),
                            ResponseCode.ACCOUNT_OR_PASSWORD_INCORRECT);

            out.append(JsonUtil.serialize(json));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
