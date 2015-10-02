package com.insoul.copartner.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.insoul.copartner.security.ContextUtil;
import com.insoul.copartner.util.IpUtil;

public abstract class BaseServiceImpl {
    
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;

    protected long getUserId() {
        return ContextUtil.getUserId();
    }

    protected long getIp() {
        String ip = IpUtil.getIpAddr(request);

        return IpUtil.ip2Long(ip);
    }
}
