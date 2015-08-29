package com.insoul.copartner.vo;

import java.io.Serializable;

public class TutorDetailVO implements Serializable {

    private static final long serialVersionUID = -7843640491980037840L;

    private Long tutorId;

    private String name;

    private String avatar;

    private IndustryDomainVO domain;

    private String email;

    private String mobile;

    private String introduction;

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public IndustryDomainVO getDomain() {
        return domain;
    }

    public void setDomain(IndustryDomainVO domain) {
        this.domain = domain;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
