package com.insoul.copartner.vo.request;

import java.io.Serializable;

public class ResumeRequest implements Serializable {

    private static final long serialVersionUID = -3228208197255216006L;

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
