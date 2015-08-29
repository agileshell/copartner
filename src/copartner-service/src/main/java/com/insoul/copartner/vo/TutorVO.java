package com.insoul.copartner.vo;

import java.io.Serializable;

public class TutorVO implements Serializable {

    private static final long serialVersionUID = -3487800612803345223L;

    private Long tutorId;

    private String name;

    private String avatar;

    private IndustryDomainVO domain;

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

}
