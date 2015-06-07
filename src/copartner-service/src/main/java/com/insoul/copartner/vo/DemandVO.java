package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class DemandVO implements Serializable {

    private static final long serialVersionUID = -3683219716155010336L;

    private UserBriefVO user;

    private String content;

    private Byte type;

    private String status;

    private Long likeCount;

    private Long commentCount;

    private Set<UserLeanVO> likers;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    public UserBriefVO getUser() {
        return user;
    }

    public void setUser(UserBriefVO user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Set<UserLeanVO> getLikers() {
        return likers;
    }

    public void setLikers(Set<UserLeanVO> likers) {
        this.likers = likers;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
