package com.insoul.copartner.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.insoul.copartner.util.IpUtil;

public abstract class BaseServiceImpl {

    @Autowired
    protected HttpServletRequest request;

    protected long getUserId() {
        return 0L;
        // return ContextUtil.getUserId();
    }

    protected long getIp() {
        String ip = IpUtil.getIpAddr(request);

        return IpUtil.ip2Long(ip);
    }
}
