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

    private Long roleId;

    private String idNumber;// 身份证号

    private String idPicture;// 身份图片

    private String authenticationInfo;// 认证说明

    private Long professionId = 1L;// 1:学术型 2:实业型

    private String investmentOrg;// 投资机构

    private String investmentStyle;// 投资风格

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

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

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public String getInvestmentOrg() {
        return investmentOrg;
    }

    public void setInvestmentOrg(String investmentOrg) {
        this.investmentOrg = investmentOrg;
    }

    public String getInvestmentStyle() {
        return investmentStyle;
    }

    public void setInvestmentStyle(String investmentStyle) {
        this.investmentStyle = investmentStyle;
    }

}