package com.insoul.copartner.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    private static final String SECURITY_USERNAME_DELIMITER = "!_";

    public static void login(UserDetails details, HttpServletRequest request, HttpServletResponse response,
            HttpSession session) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 存放authentication到SecurityContextHolder
        securityContext.setAuthentication(authentication);

        // 在session中存放security context,方便同一个session中控制用户的其他操作
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }

    public static String get3rdSecurityUserName(int providerId, long userId) {
        String providerName = providerId == 1 ? "qq" : (providerId == 1 ? "sina" : "weixin");

        return new StringBuilder(providerName).append(SECURITY_USERNAME_DELIMITER).append(userId).toString();
    }

    public static int getUserIdFrom3rdSecurityUserName(String username) {
        String channels[] = {"qq", "sina", "weixin"};
        for (String channel : channels) {
            if (username.startsWith(channel.concat(SECURITY_USERNAME_DELIMITER))) {
                String[] credentials = username.split(SECURITY_USERNAME_DELIMITER);
                return credentials.length >= 2 ? Integer.valueOf(credentials[1]) : 0;
            }
        }

        return 0;
    }
}
