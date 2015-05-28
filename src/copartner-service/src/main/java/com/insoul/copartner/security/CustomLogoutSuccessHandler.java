package com.insoul.copartner.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.util.ResponseUtil;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setContentType(CommonConstant.APPLICATION_JSON);
        PrintWriter out = null;
        try {
            out = response.getWriter();

            JSONObject json = ResponseUtil.jsonSucceed(null);

            out.append(json.toString());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
