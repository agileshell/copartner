package com.insoul.copartner.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.util.IpUtil;
import com.insoul.copartner.util.JsonUtil;
import com.insoul.copartner.util.ResponseUtil;

public class AuthenticationSuccessHandler implements
        org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(value = "transactionManager")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        User user = userDao.get(ContextUtil.getUserId());
        user.setLastLogin(new Date());
        user.setLastIp(IpUtil.ip2Long(IpUtil.getIpAddr(request)));

        response.setContentType(CommonConstant.APPLICATION_JSON);
        PrintWriter out = null;
        try {
            out = response.getWriter();

            Map<String, Object> json = ResponseUtil.jsonSucceed(null);

            out.append(JsonUtil.serialize(json));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
