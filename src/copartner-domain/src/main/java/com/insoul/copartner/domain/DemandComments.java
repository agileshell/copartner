package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "demand_comments", catalog = "copartner")
public class DemandComments extends BaseEntity {

    private static final long serialVersionUID = -4693640761294017450L;

    @Column(name = "demand_id", nullable = false)
    private Long demandId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "parent_id", nullable = false)
    private Long parentId = 0L;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String status = "active";

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((demandId == null) ? 0 : demandId.hashCode());
        result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        DemandComments other = (DemandComments) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (demandId == null) {
            if (other.demandId != null)
                return false;
        } else if (!demandId.equals(other.demandId))
            return false;
        if (parentId == null) {
            if (other.parentId != null)
                return false;
        } else if (!parentId.equals(other.parentId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
