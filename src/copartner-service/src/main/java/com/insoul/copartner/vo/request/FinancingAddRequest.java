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

    private Long projectId;// 关联的项目编号

    private String businessLicense;// 营业执照号

    private String businessLicenseUrl;// 营业执照号

    private String businessPlan;// 商业计划书

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ProjectName == null) ? 0 : ProjectName.hashCode());
        result = prime * result + ((advantage == null) ? 0 : advantage.hashCode());
        result = prime * result + ((businessLicense == null) ? 0 : businessLicense.hashCode());
        result = prime * result + ((businessLicenseUrl == null) ? 0 : businessLicenseUrl.hashCode());
        result = prime * result + ((businessPlan == null) ? 0 : businessPlan.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((financingPhaseId == null) ? 0 : financingPhaseId.hashCode());
        result = prime * result + ((funding == null) ? 0 : funding.hashCode());
        result = prime * result + ((hasBusinessRegistered == null) ? 0 : hasBusinessRegistered.hashCode());
        result = prime * result + ((industryDomainId == null) ? 0 : industryDomainId.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = prime * result + ((teamSizeId == null) ? 0 : teamSizeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FinancingAddRequest other = (FinancingAddRequest) obj;
        if (ProjectName == null) {
            if (other.ProjectName != null)
                return false;
        } else if (!ProjectName.equals(other.ProjectName))
            return false;
        if (advantage == null) {
            if (other.advantage != null)
                return false;
        } else if (!advantage.equals(other.advantage))
            return false;
        if (businessLicense == null) {
            if (other.businessLicense != null)
                return false;
        } else if (!businessLicense.equals(other.businessLicense))
            return false;
        if (businessLicenseUrl == null) {
            if (other.businessLicenseUrl != null)
                return false;
        } else if (!businessLicenseUrl.equals(other.businessLicenseUrl))
            return false;
        if (businessPlan == null) {
            if (other.businessPlan != null)
                return false;
        } else if (!businessPlan.equals(other.businessPlan))
            return false;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
        if (contactPerson == null) {
            if (other.contactPerson != null)
                return false;
        } else if (!contactPerson.equals(other.contactPerson))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (financingPhaseId == null) {
            if (other.financingPhaseId != null)
                return false;
        } else if (!financingPhaseId.equals(other.financingPhaseId))
            return false;
        if (funding == null) {
            if (other.funding != null)
                return false;
        } else if (!funding.equals(other.funding))
            return false;
        if (hasBusinessRegistered == null) {
            if (other.hasBusinessRegistered != null)
                return false;
        } else if (!hasBusinessRegistered.equals(other.hasBusinessRegistered))
            return false;
        if (industryDomainId == null) {
            if (other.industryDomainId != null)
                return false;
        } else if (!industryDomainId.equals(other.industryDomainId))
            return false;
        if (locationId == null) {
            if (other.locationId != null)
                return false;
        } else if (!locationId.equals(other.locationId))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        if (teamSizeId == null) {
            if (other.teamSizeId != null)
                return false;
        } else if (!teamSizeId.equals(other.teamSizeId))
            return false;
        return true;
    }

}
