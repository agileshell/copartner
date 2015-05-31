package com.insoul.copartner.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project", catalog = "copartner")
public class Project extends BaseEntity {

    private static final long serialVersionUID = 8685184554163466793L;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column
    private String logo;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String status = "active";

    @Column(name = "project_phase_id", nullable = false)
    private Long projectPhaseId;

    @Column(name = "init_Date", nullable = false)
    private Date initDate;

    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Column(name = "full_location", nullable = true)
    private String fullLocation;

    @Column(name = "industry_domain_id", nullable = false)
    private Long industryDomainId;

    @Column(name = "like_count", nullable = false)
    private Long likeCount = 0L;

    @Column(name = "comment_count", nullable = false)
    private Long commentCount = 0L;

    @Column(name = "key_words")
    private String keyWords;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProjectPhaseId() {
        return projectPhaseId;
    }

    public void setProjectPhaseId(Long projectPhaseId) {
        this.projectPhaseId = projectPhaseId;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((commentCount == null) ? 0 : commentCount.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((fullLocation == null) ? 0 : fullLocation.hashCode());
        result = prime * result + ((industryDomainId == null) ? 0 : industryDomainId.hashCode());
        result = prime * result + ((initDate == null) ? 0 : initDate.hashCode());
        result = prime * result + ((keyWords == null) ? 0 : keyWords.hashCode());
        result = prime * result + ((likeCount == null) ? 0 : likeCount.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((logo == null) ? 0 : logo.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((projectPhaseId == null) ? 0 : projectPhaseId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        if (commentCount == null) {
            if (other.commentCount != null)
                return false;
        } else if (!commentCount.equals(other.commentCount))
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
        if (initDate == null) {
            if (other.initDate != null)
                return false;
        } else if (!initDate.equals(other.initDate))
            return false;
        if (keyWords == null) {
            if (other.keyWords != null)
                return false;
        } else if (!keyWords.equals(other.keyWords))
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
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
