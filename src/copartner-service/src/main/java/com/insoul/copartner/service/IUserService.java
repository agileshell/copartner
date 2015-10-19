package com.insoul.copartner.service;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.LoginResponse;
import com.insoul.copartner.vo.ResumeVO;
import com.insoul.copartner.vo.UserDetailVO;
import com.insoul.copartner.vo.request.ResumeRequest;
import com.insoul.copartner.vo.request.SignInByThirdPartRequest;
import com.insoul.copartner.vo.request.UserAddRequest;
import com.insoul.copartner.vo.request.UserAuthenticateRequest;
import com.insoul.copartner.vo.request.UserProfileUpdateRequest;

public interface IUserService {

    void userAuthenticate(UserAuthenticateRequest request) throws CException;

    UserDetailVO register(UserAddRequest userAddRequest) throws CException;

    void retrievePassword(String account) throws CException;

    void resetPassword(String account, String code, String password) throws CException;

    void changePassword(String oldPassword, String password) throws CException;

    void updateProfile(UserProfileUpdateRequest profileUpdateRequest) throws CException;

    UserDetailVO getUserProfileDetail();

    UserDetailVO getUserProfileDetail(Long userId) throws CException;

    void updateEducationResume(List<ResumeRequest> requestDatas);

    void updateWorkResume(List<ResumeRequest> requestDatas);

    Set<ResumeVO> listUserEducationResume();

    Set<ResumeVO> listUserWorkResume();

    LoginResponse loginUse3rdOauth(SignInByThirdPartRequest signInBy3rdPartRequest) throws CException;

}
