package com.insoul.copartner.vo;

import java.io.Serializable;

public class ContestEntryDetailVO implements Serializable {

    private static final long serialVersionUID = -2349115796793658537L;

    private Long id;

    private ProjectDetailVO project;

    private UserLeanVO user;

    private long votes;

    private long tutorVotes;

    private long investorVotes;

    private Boolean hasBusinessRegistered = false;

    private String businessLicense;

    private String businessLicenseImg;

    private boolean isVote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectDetailVO getProject() {
        return project;
    }

    public void setProject(ProjectDetailVO project) {
        this.project = project;
    }

    public UserLeanVO getUser() {
        return user;
    }

    public void setUser(UserLeanVO user) {
        this.user = user;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public long getTutorVotes() {
        return tutorVotes;
    }

    public void setTutorVotes(long tutorVotes) {
        this.tutorVotes = tutorVotes;
    }

    public long getInvestorVotes() {
        return investorVotes;
    }

    public void setInvestorVotes(long investorVotes) {
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

    public boolean isVote() {
        return isVote;
    }

    public void setVote(boolean isVote) {
        this.isVote = isVote;
    }

}
