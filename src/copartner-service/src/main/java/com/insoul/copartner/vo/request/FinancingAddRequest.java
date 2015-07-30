package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class FinancingAddRequest implements Serializable {

    private static final long serialVersionUID = -8110897703800874369L;

    @NotBlank
    @StringLength(max = 50)
    private String ProjectName;// 项目名称

    @NotNull
    private Long teamSizeId;// 团队规模

    @NotNull
    private Long locationId;// 所属地区

    @NotNull
    private Long industryDomainId;// 行业

    @NotNull
    private Long financingPhaseId;// 阶段

    @NotNull
    private Boolean hasBusinessRegistered;// 是否工商注册

    @NotBlank
    @StringLength(max = 200)
    private String content;// 实施条件

    @StringLength(max = 200)
    private String advantage;// 优势

    private Float funding;// 意向资金

    @StringLength(max = 30)
    private String contactPerson;// 联系人

    @StringLength(max = 30)
    private String contact;// 联系方式

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
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

    public Long getIndustryDomainId() {
        return industryDomainId;
    }

    public void setIndustryDomainId(Long industryDomainId) {
        this.industryDomainId = industryDomainId;
    }

    public Long getFinancingPhaseId() {
        return financingPhaseId;
    }

    public void setFinancingPhaseId(Long financingPhaseId) {
        this.financingPhaseId = financingPhaseId;
    }

    public Boolean getHasBusinessRegistered() {
        return hasBusinessRegistered;
    }

    public void setHasBusinessRegistered(Boolean hasBusinessRegistered) {
        this.hasBusinessRegistered = hasBusinessRegistered;
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
