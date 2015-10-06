package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 融智
 */
@Entity
@Table(name = "demand", catalog = "copartner")
public class Demand extends BaseEntity {

    private static final long serialVersionUID = -7917543729540044208L;

    @Column(name = "user_id", nullable = false)
    private Long userId;// 创建者，用户ID

    @Column(name = "project_name", nullable = false)
    private String projectName;// 项目名称

    @Column(nullable = false)
    private String status = "active";// 状态 active 可用，inactive不可用, deleted删除

    @Column(name = "team_size", nullable = false)
    private Long teamSizeId;// 团队规模

    @Column(name = "industry_domain_id", nullable = false)
    private Long industryDomainId;// 行业

    @Column(name = "has_business_registered", nullable = false)
    private Boolean hasBusinessRegistered = false;// 是否工商注册

    @Column(name = "location_id", nullable = false)
    private Long locationId;// 所属地区

    @Column(name = "full_location", nullable = true)
    private String fullLocation;// 地区缓存

    @Column(nullable = false)
    private String advantage;// 优势

    @Column(nullable = false)
    private String content;// 融智要求

    @Column(nullable = false)
    private String reward;// 回报

    @Column(name = "contact_person")
    private String contactPerson;// 联系人

    @Column(name = "contact")
    private String contact;// 联系方式

    @Column(name = "like_count", nullable = false)
    private Long likeCount = 0L;

    @Column(name = "comment_count", nullable = false)
    private Long commentCount = 0L;

    @Column(name = "beused")
    private byte beused = 0;// 是否被使用 0:未使用 1:已使用

    @Column(name = "project_id")
    private Long projectId;// 关联的项目编号

    @Column(name = "business_license")
    private String businessLicense;// 营业执照号

    @Column(name = "business_license_url")
    private String businessLicenseUrl;// 营业执照

    @Column(name = "business_plan")
    private String businessPlan;// 商业计划书

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

    public Long getTeamSizeId() {
        return teamSizeId;
    }

    public void setTeamSizeId(Long teamSizeId) {
        this.teamSizeId = teamSizeId;
    }

    public Long getIndustryDomainId() {
        return industryDomainId;
    }

    public void setIndustryDomainId(Long industryDomainId) {
        this.industryDomainId = industryDomainId;
    }

    public Boolean getHasBusinessRegistered() {
        return hasBusinessRegistered;
    }

    public void setHasBusinessRegistered(Boolean hasBusinessRegistered) {
        this.hasBusinessRegistered = hasBusinessRegistered;
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

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
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

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public byte getBeused() {
        return beused;
    }

    public void setBeused(byte beused) {
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
        int result = super.hashCode();
        result = prime * result + ((advantage == null) ? 0 : advantage.hashCode());
        result = prime * result + beused;
        result = prime * result + ((businessLicense == null) ? 0 : businessLicense.hashCode());
        result = prime * result + ((businessLicenseUrl == null) ? 0 : businessLicenseUrl.hashCode());
        result = prime * result + ((businessPlan == null) ? 0 : businessPlan.hashCode());
        result = prime * result + ((commentCount == null) ? 0 : commentCount.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((fullLocation == null) ? 0 : fullLocation.hashCode());
        result = prime * result + ((hasBusinessRegistered == null) ? 0 : hasBusinessRegistered.hashCode());
        result = prime * result + ((industryDomainId == null) ? 0 : industryDomainId.hashCode());
        result = prime * result + ((likeCount == null) ? 0 : likeCount.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
        result = prime * result + ((reward == null) ? 0 : reward.hashCode());
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
        Demand other = (Demand) obj;
        if (advantage == null) {
            if (other.advantage != null)
                return false;
        } else if (!advantage.equals(other.advantage))
            return false;
        if (beused != other.beused)
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
        if (commentCount == null) {
            if (other.commentCount != null)
                return false;
        } else if (!commentCount.equals(other.commentCount))
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
        if (fullLocation == null) {
            if (other.fullLocation != null)
                return false;
        } else if (!fullLocation.equals(other.fullLocation))
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
        if (likeCount == null) {
            if (other.likeCount != null)
                return false;
        } else if (!likeCount.equals(other.likeCount))
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
        if (reward == null) {
            if (other.reward != null)
                return false;
        } else if (!reward.equals(other.reward))
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