package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class ProjectAddRequest implements Serializable {

    private static final long serialVersionUID = -2827520776964256863L;

    @NotBlank
    @StringLength(max = 50)
    private String name;// 项目名称

    private String logo;// 项目LOGO

    @NotNull
    private Long projectPhaseId;// 阶段

    @NotNull
    private Long locationId;// 所属地区

    @NotNull
    private Long industryDomainId;// 行业

    @NotBlank
    @StringLength(max = 200)
    private String content;// 实施条件

    @StringLength(max = 200)
    private String advantage;// 优势

    @NotNull
    private Long teamSizeId;// 团队规模

    @StringLength(max = 30)
    private String contactPerson;// 联系人

    @StringLength(max = 30)
    private String contact;// 联系方式

    // 以下是比赛相关
    private Boolean hasBusinessRegistered;// 是否工商注册
    private String businessLicense;// 营业执照号
    private String businessLicenseImg;// 营业执照
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getProjectPhaseId() {
        return projectPhaseId;
    }

    public void setProjectPhaseId(Long projectPhaseId) {
        this.projectPhaseId = projectPhaseId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getIndustryDomainId() {
        return industryDomainId;
    }

    public void setIndustryDomainId(Long industryDomainId) {
        this.industryDomainId = industryDomainId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public Long getTeamSizeId() {
        return teamSizeId;
    }

    public void setTeamSizeId(Long teamSizeId) {
        this.teamSizeId = teamSizeId;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
