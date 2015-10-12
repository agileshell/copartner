package com.insoul.copartner.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RequirementLikersId implements Serializable {

    private static final long serialVersionUID = -1950068340295557051L;

    @Column(name = "requirement_id", nullable = false)
    private Long requirementId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requirementId == null) ? 0 : requirementId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequirementLikersId other = (RequirementLikersId) obj;
        if (requirementId == null) {
            if (other.requirementId != null)
                return false;
        } else if (!requirementId.equals(other.requirementId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
