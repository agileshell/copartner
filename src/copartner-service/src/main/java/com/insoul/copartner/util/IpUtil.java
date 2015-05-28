package com.insoul.copartner.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IpUtil {

    private IpUtil() {
        // empty
    }

    public static String getIpAddr(final HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static Long ip2Long(final String ip) {
        Long ipLong = 0L;
        if (StringUtils.isNotBlank(ip)) {
            try {
                String[] ips = ip.split("\\.");
                ipLong = (Long.parseLong(ips[0]) << 24) + (Long.parseLong(ips[1]) << 16)
                        + (Long.parseLong(ips[2]) << 8) + Long.parseLong(ips[3]);
            } catch (Exception e) {
                ipLong = 0L;
            }
        }

        return ipLong;
    }

}
