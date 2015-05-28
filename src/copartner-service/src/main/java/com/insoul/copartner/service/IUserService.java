package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.request.UserAddRequest;

public interface IUserService {
    long register(UserAddRequest userAddRequest) throws CException;

    void retrievePassword(String account) throws CException;

    void resetPassword(String account, String code, String password) throws CException;

    void changePassword(String oldPassword, String password) throws CException;

}
