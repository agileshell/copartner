package com.insoul.copartner.vo;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 8585470927148178261L;

    private Long userId;

    private String name;

    private String avatar;

    private Long roleId;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getImId() {
        return imId;
    }

    public void setImId(Long imId) {
        this.imId = imId;
    }

}
