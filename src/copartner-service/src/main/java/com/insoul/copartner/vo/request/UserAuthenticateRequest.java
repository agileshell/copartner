package com.insoul.copartner.vo.request;

import java.io.Serializable;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月30日 下午5:30:29
 */
public class UserAuthenticateRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String idNumber;// 身份证号
    
    private String idPicture;// 身份图片
    
    private String authenticationInfo;// 认证说明

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(String idPicture) {
        this.idPicture = idPicture;
    }

    public String getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(String authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }
}