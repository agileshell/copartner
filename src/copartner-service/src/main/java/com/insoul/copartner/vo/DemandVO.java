package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Set;

public class DemandVO implements Serializable {

    private static final long serialVersionUID = -3683219716155010336L;

    private UserBriefVO user;

    private String content;

    private Byte type;

    private String status;

    private Long likeCount;

    private Long commentCount;

    private Set<UserLeanVO> likers;

    private Set<DemandCommentVO> comments;

    private String created;

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

    public Set<DemandCommentVO> getComments() {
        return comments;
    }

    public void setComments(Set<DemandCommentVO> comments) {
        this.comments = comments;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}