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

    @Column(name = "beused")
    private Byte beused = 0;// 是否被使用 0:未使用 1:已使用

    @Column(name = "project_id")
    private Long projectId;// 关联的项目编号

    @Column(name = "business_license")
    private String businessLicense;// 营业执照号

    @Column(name = "business_plan")
    private String businessPlan;// 商业计划书

    public Byte getBeused() {
        return beused;
    }

    public void setBeused(Byte beused) {
        this.beused = beused;
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

    public String getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(String businessPlan) {
        this.businessPlan = businessPlan;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((advantage == null) ? 0 : advantage.hashCode());
        result = prime * result + ((businessLicense == null) ? 0 : businessLicense.hashCode());
        result = prime * result + ((businessPlan == null) ? 0 : businessPlan.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((financingPhaseId == null) ? 0 : financingPhaseId.hashCode());
        result = prime * result + ((fullLocation == null) ? 0 : fullLocation.hashCode());
        result = prime * result + ((funding == null) ? 0 : funding.hashCode());
        result = prime * result + ((hasBusinessRegistered == null) ? 0 : hasBusinessRegistered.hashCode());
        result = prime * result + ((industryDomainId == null) ? 0 : industryDomainId.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((teamSizeId == null) ? 0 : teamSizeId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Financing other = (Financing) obj;
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
        if (fullLocation == null) {
            if (other.fullLocation != null)
                return false;
        } else if (!fullLocation.equals(other.fullLocation))
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
        if (projectName == null) {
            if (other.projectName != null)
                return false;
        } else if (!projectName.equals(other.projectName))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (teamSizeId == null) {
            if (other.teamSizeId != null)
                return false;
        } else if (!teamSizeId.equals(other.teamSizeId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
}
