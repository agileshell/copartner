package com.insoul.copartner.vo;

import java.io.Serializable;

public class CommentVO implements Serializable {

    private static final long serialVersionUID = -3464997103418112764L;

    private Long id;

    private Long parentId;

    private String content;

    private UserLeanVO commentor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserLeanVO getCommentor() {
        return commentor;
    }

    public void setCommentor(UserLeanVO commentor) {
        this.commentor = commentor;
    }

}
