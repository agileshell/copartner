package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 融资
 */
@Entity
@Table(name = "financing", catalog = "copartner")
public class Financing extends BaseEntity {

    private static final long serialVersionUID = -5187615027598667673L;

    @Column(name = "user_id", nullable = false)
    private Long userId;// 创建者，用户ID

    @Column(name = "project_name", nullable = false)
    private String projectName;// 项目名称

    @Column(nullable = false)
    private String status = "active";// 状态 active 可用，inactive不可用

    @Column(name = "industry_domain_id", nullable = false)
    private Long industryDomainId;// 行业

    @Column(name = "team_size", nullable = false)
    private Long teamSizeId;// 团队规模

    @Column(name = "location_id", nullable = false)
    private Long locationId;// 所属地区

    @Column(name = "full_location", nullable = true)
    private String fullLocation;// 地区缓存

    @Column(name = "has_business_registered", nullable = false)
    private Boolean hasBusinessRegistered = false;// 是否工商注册

    @Column(name = "financing_phase_id", nullable = false)
    private Long financingPhaseId;// 融资阶段

    @Column(nullable = false)
    private String advantage;// 优势

    @Column(nullable = false)
    private String content;// 融资要求

    private Float funding;// 意向资金

    @Column(name = "contact_person")
    private String contactPerson;// 联系人

    @Column(name = "contact")
    private String contact;// 联系方式

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIndustryDomainId() {
        return industryDomainId;
    }

    public void setIndustryDomainId(Long industryDomainId) {
        this.industryDomainId = industryDomainId;
    }

    public Long getTeamSizeId() {
        return teamSizeId;
    }

    public void setTeamSizeId(Long teamSizeId) {
        this.teamSizeId = teamSizeId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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

}
