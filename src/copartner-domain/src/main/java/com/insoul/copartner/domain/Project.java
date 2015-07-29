package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * 项目
 */
@Entity
@Table(name = "project", catalog = "copartner")
public class Project extends BaseEntity {

    private static final long serialVersionUID = 8685184554163466793L;

    @Column(name = "user_id", nullable = false)
    private Long userId;// 创建者，用户ID

    @Column(nullable = false)
    private String name;// 项目名称

    @Column
    private String logo;// 项目LOGO

    @Column(nullable = false)
    private String status = "active";// 状态 active 可用，inactive不可用

    @Column(nullable = false)
    private String advantage;// 优势

    @Column(nullable = false)
    private String content;// 实施条件

    @Column(name = "project_phase_id", nullable = false)
    private Long projectPhaseId;// 阶段

    @Column(name = "team_size", nullable = false)
    private Long teamSizeId;// 团队规模

    @Column(name = "location_id", nullable = false)
    private Long locationId;// 所属地区

    @Column(name = "full_location", nullable = true)
    private String fullLocation;// 地区缓存

    @Column(name = "industry_domain_id", nullable = false)
    private Long industryDomainId;// 行业

    @Column(name = "like_count", nullable = false)
    private Long likeCount = 0L;// 收藏次数

    @Column(name = "comment_count", nullable = false)
    private Long commentCount = 0L;// 评论次数

    @Column(name = "contact_person")
    private String contactPerson;// 联系人

    @Column(name = "contact")
    private String contact;// 联系方式
    
    public String getShortName() {
    	if (StringUtils.length(name) < 11) {
    		return name;
    	}
    	return StringUtils.substring(name, 0, 10) + "...";
    }
    
    public String getShortContent() {
    	if (StringUtils.length(content) < 11) {
    		return content;
    	}
    	return StringUtils.substring(content, 0, 10) + "...";
    }
    
    public String getShortAdvantage() {
    	if (StringUtils.length(advantage) < 11) {
    		return advantage;
    	}
    	return StringUtils.substring(advantage, 0, 10) + "...";
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getProjectPhaseId() {
        return projectPhaseId;
    }

    public void setProjectPhaseId(Long projectPhaseId) {
        this.projectPhaseId = projectPhaseId;
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

    public Long getIndustryDomainId() {
        return industryDomainId;
    }

    public void setIndustryDomainId(Long industryDomainId) {
        this.industryDomainId = industryDomainId;
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
        result = prime * result + ((commentCount == null) ? 0 : commentCount.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((fullLocation == null) ? 0 : fullLocation.hashCode());
        result = prime * result + ((industryDomainId == null) ? 0 : industryDomainId.hashCode());
        result = prime * result + ((likeCount == null) ? 0 : likeCount.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((logo == null) ? 0 : logo.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((projectPhaseId == null) ? 0 : projectPhaseId.hashCode());
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
        Project other = (Project) obj;
        if (advantage == null) {
            if (other.advantage != null)
                return false;
        } else if (!advantage.equals(other.advantage))
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
        if (logo == null) {
            if (other.logo != null)
                return false;
        } else if (!logo.equals(other.logo))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (projectPhaseId == null) {
            if (other.projectPhaseId != null)
                return false;
        } else if (!projectPhaseId.equals(other.projectPhaseId))
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
