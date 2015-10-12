package com.insoul.copartner.vo;

import java.io.Serializable;

public class ContestEntryDetailVO implements Serializable {

    private static final long serialVersionUID = -2349115796793658537L;

    private Long id;

    private ProjectLeanVO project;

    private UserLeanVO user;

    private Long votes;

    private Boolean hasBusinessRegistered = false;

    private String businessLicense;

    private String businessLicenseImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectLeanVO getProject() {
        return project;
    }

    public void setProject(ProjectLeanVO project) {
        this.project = project;
    }

    public UserLeanVO getUser() {
        return user;
    }

    public void setUser(UserLeanVO user) {
        this.user = user;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
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

}
