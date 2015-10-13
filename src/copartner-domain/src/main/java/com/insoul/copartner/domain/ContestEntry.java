package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 参赛项目
 */
@Entity
@Table(name = "contest_entry", catalog = "copartner")
@NamedQueries({
        @NamedQuery(name = "ContestEntry.getByContestAndUser", query = "FROM ContestEntry WHERE contestId = :contestId AND userId = :userId") })
public class ContestEntry extends BaseEntity {

    private static final long serialVersionUID = 1738371565002304322L;

    @Column(name = "project_id", nullable = false)
    private Long projectId;// 参赛项目

    @Column(name = "contest_id", nullable = false)
    private Long contestId;// 大赛ID

    @Column(name = "user_id", nullable = false)
    private Long userId;// 参赛人

    @Column(name = "status", nullable = false)
    private String status = "active";// 状态 active, inactive, deleted

    @Column(name = "votes", nullable = false)
    private Long votes = 0L;

    @Column(name = "tutor_votes", nullable = false)
    private Long tutorVotes = 0L; // 导师投票次数

    @Column(name = "investor_votes", nullable = false)
    private Long investorVotes = 0L;// 投资人投票次数

    @Column(name = "has_business_registered", nullable = false)
    private Boolean hasBusinessRegistered = false;// 是否工商注册

    @Column(name = "business_license")
    private String businessLicense;// 营业执照号

    @Column(name = "business_license_img")
    private String businessLicenseImg;// 营业执照

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getTutorVotes() {
        return tutorVotes;
    }

    public void setTutorVotes(Long tutorVotes) {
        this.tutorVotes = tutorVotes;
    }

    public Long getInvestorVotes() {
        return investorVotes;
    }

    public void setInvestorVotes(Long investorVotes) {
        this.investorVotes = investorVotes;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((businessLicense == null) ? 0 : businessLicense.hashCode());
        result = prime * result + ((businessLicenseImg == null) ? 0 : businessLicenseImg.hashCode());
        result = prime * result + ((contestId == null) ? 0 : contestId.hashCode());
        result = prime * result + ((hasBusinessRegistered == null) ? 0 : hasBusinessRegistered.hashCode());
        result = prime * result + ((investorVotes == null) ? 0 : investorVotes.hashCode());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((tutorVotes == null) ? 0 : tutorVotes.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((votes == null) ? 0 : votes.hashCode());
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
        ContestEntry other = (ContestEntry) obj;
        if (businessLicense == null) {
            if (other.businessLicense != null)
                return false;
        } else if (!businessLicense.equals(other.businessLicense))
            return false;
        if (businessLicenseImg == null) {
            if (other.businessLicenseImg != null)
                return false;
        } else if (!businessLicenseImg.equals(other.businessLicenseImg))
            return false;
        if (contestId == null) {
            if (other.contestId != null)
                return false;
        } else if (!contestId.equals(other.contestId))
            return false;
        if (hasBusinessRegistered == null) {
            if (other.hasBusinessRegistered != null)
                return false;
        } else if (!hasBusinessRegistered.equals(other.hasBusinessRegistered))
            return false;
        if (investorVotes == null) {
            if (other.investorVotes != null)
                return false;
        } else if (!investorVotes.equals(other.investorVotes))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (tutorVotes == null) {
            if (other.tutorVotes != null)
                return false;
        } else if (!tutorVotes.equals(other.tutorVotes))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (votes == null) {
            if (other.votes != null)
                return false;
        } else if (!votes.equals(other.votes))
            return false;
        return true;
    }

}
