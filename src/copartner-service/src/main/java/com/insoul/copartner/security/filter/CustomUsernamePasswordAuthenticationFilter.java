package com.insoul.copartner.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "account";

    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        if (username == null) {
            username = "";
        }
        String password = obtainPassword(request);
        if (password == null) {
            password = "";
        }

        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = request.getHeader(SPRING_SECURITY_FORM_USERNAME_KEY);
        if (username == null) {
            username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
        }
        if (username != null) {
            username = username.toLowerCase();
        }

        return username;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = request.getHeader(SPRING_SECURITY_FORM_PASSWORD_KEY);
        if (password == null) {
            password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        }

        return password;
    }
}
