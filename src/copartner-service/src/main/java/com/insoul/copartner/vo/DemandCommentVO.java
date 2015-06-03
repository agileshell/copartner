package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class DemandCommentVO implements Serializable {

    private static final long serialVersionUID = 4322106419048694307L;

    private UserLeanVO user;

    private Long parentId;

    private String content;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    public UserLeanVO getUser() {
        return user;
    }

    public void setUser(UserLeanVO user) {
        this.user = user;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
