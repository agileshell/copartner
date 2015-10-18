package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ContestRegisterRequest implements Serializable {

    private static final long serialVersionUID = -7880711302179561778L;

    @NotNull
    private Long projectId;// 参赛项目

    @NotNull
    private Boolean hasBusinessRegistered;// 是否工商注册

    private String businessLicense;// 营业执照号

    private String businessLicenseImg;// 营业执照

    // 以下是比赛相关
    private Boolean registerContest;
    private String locationCampus;// 所在市县或园区
    private String instance;// 创业实体名称
    private String legalFormation;// 企业法律形态
    private Integer employqty;// 吸纳就业人数
    private String regtime;// 注册时间
    private String legalPerson;// 法定代表人
    private String userCategory;// 人员类别
    private String idNumber;// 身份证
    private String bankName;// 开户行
    private String bankUserName;// 开户名
    private String bankAccount;// 账号
    private String supportMoney;// 申请扶持金额

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Boolean getHasBusinessRegistered() {
        return hasBusinessRegistered;
    }

    public void setHasBusinessRegistered(Boolean hasBusinessRegistered) {
        this.hasBusinessRegistered = hasBusinessRegistered;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public Boolean getRegisterContest() {
        return registerContest;
    }

    public void setRegisterContest(Boolean registerContest) {
        this.registerContest = registerContest;
    }

    public String getLocationCampus() {
        return locationCampus;
    }

    public void setLocationCampus(String locationCampus) {
        this.locationCampus = locationCampus;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getLegalFormation() {
        return legalFormation;
    }

    public void setLegalFormation(String legalFormation) {
        this.legalFormation = legalFormation;
    }

    public Integer getEmployqty() {
        return employqty;
    }

    public void setEmployqty(Integer employqty) {
        this.employqty = employqty;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(String supportMoney) {
        this.supportMoney = supportMoney;
    }

}
