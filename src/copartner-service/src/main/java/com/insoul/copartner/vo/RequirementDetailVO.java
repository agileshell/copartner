package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class RequirementDetailVO implements Serializable {

    private static final long serialVersionUID = 2938064603299380029L;

    private long id;

    private Integer type = 1;// 1加入团队， 2寻求搭档， 3寻求融资， 4寻求融智，5投资项目

    private String content;

    private String status;

    private ProjectLeanVO project;

    private UserLeanVO user;

    private Long likeCount;

    private Long commentCount;

    private Set<UserLeanVO> likers;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    private boolean isliked;

    private List<CommentVO> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProjectLeanVO getProject() {
        return project;
    }

    public void setProject(ProjectLeanVO project) {
        this.project = project;
    }

    public UserLeanVO getUser() {
        return user;
    }

    public void setUser(UserLeanVO user) {
        this.user = user;
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

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }

}
