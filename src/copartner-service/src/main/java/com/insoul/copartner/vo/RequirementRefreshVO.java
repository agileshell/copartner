package com.insoul.copartner.vo;

import java.io.Serializable;

public class RequirementRefreshVO implements Serializable {

    private static final long serialVersionUID = -6269226729937761819L;

    private long id;

    private long likeCount;

    private long commentCount;

    private boolean isliked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

}
