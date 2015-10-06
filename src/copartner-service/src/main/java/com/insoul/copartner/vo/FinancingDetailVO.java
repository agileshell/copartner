package com.insoul.copartner.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class FinancingDetailVO {

    private Long id;

    private Long userId;

    private String projectName;

    private Long industryDomainId;// 行业

    private String industryDomainName;

    private Long teamSizeId;// 团队规模

    private String teamSizeName;

    private String fullLocation;// 地区缓存

    private Boolean hasBusinessRegistered = false;// 是否工商注册

    private Long financingPhaseId;// 融资阶段

    private String financingPhaseName;

    private String advantage;// 优势

    private String content;// 融资要求

    private Float funding;// 意向资金

    private String contactPerson;// 联系人

    private String contact;// 联系方式

    private Long projectId;// 关联的项目编号

    private String businessLicense;// 营业执照号

    private String businessLicenseUrl;// 营业执照

    private String businessPlan;// 商业计划书

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getIndustryDomainId() {
        return industryDomainId;
    }

    public void setIndustryDomainId(Long industryDomainId) {
        this.industryDomainId = industryDomainId;
    }

    public String getIndustryDomainName() {
        return industryDomainName;
    }

    public void setIndustryDomainName(String industryDomainName) {
        this.industryDomainName = industryDomainName;
    }

    public Long getTeamSizeId() {
        return teamSizeId;
    }

    public void setTeamSizeId(Long teamSizeId) {
        this.teamSizeId = teamSizeId;
    }

    public String getTeamSizeName() {
        return teamSizeName;
    }

    public void setTeamSizeName(String teamSizeName) {
        this.teamSizeName = teamSizeName;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public Boolean getHasBusinessRegistered() {
        return hasBusinessRegistered;
    }

    public void setHasBusinessRegistered(Boolean hasBusinessRegistered) {
        this.hasBusinessRegistered = hasBusinessRegistered;
    }

    public Long getFinancingPhaseId() {
        return financingPhaseId;
    }

    public void setFinancingPhaseId(Long financingPhaseId) {
        this.financingPhaseId = financingPhaseId;
    }

    public String getFinancingPhaseName() {
        return financingPhaseName;
    }

    public void setFinancingPhaseName(String financingPhaseName) {
        this.financingPhaseName = financingPhaseName;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getFunding() {
        return funding;
    }

    public void setFunding(Float funding) {
        this.funding = funding;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    public String getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(String businessPlan) {
        this.businessPlan = businessPlan;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
