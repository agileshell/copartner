package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ContestRegisterRequest implements Serializable {

    private static final long serialVersionUID = -7880711302179561778L;

    @NotNull
    private Long projectId;// 参赛项目

    @NotNull
    private Boolean hasBusinessRegistered;// 是否工商注册

    private String businessLicense;// 营业执照号

    private String businessLicenseImg;// 营业执照

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
