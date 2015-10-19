package com.insoul.copartner.vo.request;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class SignInByThirdPartRequest implements Serializable {

    private static final long serialVersionUID = 1840149131657301141L;

    @NotEmpty
    private String nickname;

    private String avatar;

    @NotEmpty
    private String accessToken;

    private Long expireIn;

    private String refreshToken;

    private String uid;

    private Integer providerId;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

}
