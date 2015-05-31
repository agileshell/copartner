package com.insoul.copartner.vo;

import java.io.Serializable;

public class ResumeVO implements Serializable {

    private static final long serialVersionUID = -8934640427475565244L;

    private String name;

    private String major;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
