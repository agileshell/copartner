package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;

public interface IVerifyCodeService {

    void sendVerifyCode(String mobile, String type) throws CException;

    void verifyCode(String mobile, String type, String code) throws CException;
}
