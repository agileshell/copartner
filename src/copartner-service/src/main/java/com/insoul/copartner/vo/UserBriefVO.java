package com.insoul.copartner.vo;

import java.io.Serializable;

public class UserBriefVO implements Serializable {

    private static final long serialVersionUID = 4814605006693792527L;

    private Long userId;

    private String name;

    private String avatar;

    private String location;

    private String role;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
