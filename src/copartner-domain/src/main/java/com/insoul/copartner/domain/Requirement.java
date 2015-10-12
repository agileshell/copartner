package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 需求
 */
@Entity
@Table(name = "requirement", catalog = "copartner")
public class Requirement extends BaseEntity {

    private static final long serialVersionUID = -3668234296156921363L;

    @Column(name = "type", nullable = false)
    private Integer type = 1;// 1加入团队， 2寻求搭档， 3寻求融资， 4寻求融智，5投资项目

    @Column(name = "project_id")
    private Long projectId;// 关联项目

    @Column(name = "content", nullable = false)
    private String content;// 需求内容

    @Column(name = "status", nullable = false)
    private String status = "active";// 状态 active,inactive

    @Column(name = "user_id", nullable = false)
    private Long userId;// 所属者

    @Column(name = "like_count", nullable = false)
    private Long likeCount = 0L;// 收藏次数

    @Column(name = "comment_count", nullable = false)
    private Long commentCount = 0L;// 评论次数

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((commentCount == null) ? 0 : commentCount.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((likeCount == null) ? 0 : likeCount.hashCode());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Requirement other = (Requirement) obj;
        if (commentCount == null) {
            if (other.commentCount != null)
                return false;
        } else if (!commentCount.equals(other.commentCount))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (likeCount == null) {
            if (other.likeCount != null)
                return false;
        } else if (!likeCount.equals(other.likeCount))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
