package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Set;

public class UserDetailVO implements Serializable {

    private static final long serialVersionUID = 5142146958295689471L;

    private Long userId;

    private String name;

    private String email;

    private String mobile;

    private String status;

    private String avatar;

    private LocationVO location;

    private String fullLocation;

    private String gender;

    private String age;

    private String introduction;

    private Boolean isEmailVerified;

    private Boolean isMobileVerified;

    private StartupStatusVO StartupStatus;

    private StartupRoleVO startupRole;

    private Set<IndustryDomainVO> domains;

    private String fullDomains;

    private Set<ResumeVO> educationResumes;

    private Set<ResumeVO> workResumes;

    private Long imId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocationVO getLocation() {
        return location;
    }

    public void setLocation(LocationVO location) {
        this.location = location;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getIsMobileVerified() {
        return isMobileVerified;
    }

    public void setIsMobileVerified(Boolean isMobileVerified) {
        this.isMobileVerified = isMobileVerified;
    }

    public StartupStatusVO getStartupStatus() {
        return StartupStatus;
    }

    public void setStartupStatus(StartupStatusVO startupStatus) {
        StartupStatus = startupStatus;
    }

    public StartupRoleVO getStartupRole() {
        return startupRole;
    }

    public void setStartupRole(StartupRoleVO startupRole) {
        this.startupRole = startupRole;
    }

    public Set<IndustryDomainVO> getDomains() {
        return domains;
    }

    public void setDomains(Set<IndustryDomainVO> domains) {
        this.domains = domains;
    }

    public String getFullDomains() {
        return fullDomains;
    }

    public void setFullDomains(String fullDomains) {
        this.fullDomains = fullDomains;
    }

    public Set<ResumeVO> getEducationResumes() {
        return educationResumes;
    }

    public void setEducationResumes(Set<ResumeVO> educationResumes) {
        this.educationResumes = educationResumes;
    }

    public Set<ResumeVO> getWorkResumes() {
        return workResumes;
    }

    public void setWorkResumes(Set<ResumeVO> workResumes) {
        this.workResumes = workResumes;
    }

    public Long getImId() {
        return imId;
    }

    public void setImId(Long imId) {
        this.imId = imId;
    }

}
