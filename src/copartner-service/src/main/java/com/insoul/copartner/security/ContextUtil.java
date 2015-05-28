package com.insoul.copartner.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.insoul.copartner.security.model.UserDetailsImpl;

public class ContextUtil {

    public static SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    public static UserDetailsImpl getUserDetail() {
        Authentication authentication = getSecurityContext().getAuthentication();
        if (null != authentication) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetailsImpl) {
                return (UserDetailsImpl) principal;
            }
        }

        return null;
    }

    public static long getUserId() {
        UserDetailsImpl userDetails = getUserDetail();
        if (null != userDetails) {
            return userDetails.getUserId();
        }

        return 0L;
    }
}
