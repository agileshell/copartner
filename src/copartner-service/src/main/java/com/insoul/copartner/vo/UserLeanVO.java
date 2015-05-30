package com.insoul.copartner.vo;

import java.io.Serializable;

public class UserLeanVO implements Serializable {

    private static final long serialVersionUID = 6088734482858348339L;

    private Long userId;

    private String name;

    private String avatar;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
