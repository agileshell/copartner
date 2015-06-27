package com.insoul.copartner.vo;

import java.io.Serializable;

public class FriendVO implements Serializable {

    private static final long serialVersionUID = 4814605006693792527L;

    private Long friendId;

    private String name;

    private String avatar;

    private Long imId;

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
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

    public Long getImId() {
        return imId;
    }

    public void setImId(Long imId) {
        this.imId = imId;
    }

}
