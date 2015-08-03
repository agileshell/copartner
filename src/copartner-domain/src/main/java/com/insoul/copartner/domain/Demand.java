package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

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

    public String getShortName() {
    	if (StringUtils.length(projectName) < 11) {
    		return projectName;
    	}
    	return StringUtils.substring(projectName, 0, 10) + "...";
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

}
