package com.insoul.copartner.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectLikersId implements Serializable {

    private static final long serialVersionUID = -5250808001835545359L;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public ProjectLikersId(Long projectId, Long userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
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
        ProjectLikersId other = (ProjectLikersId) obj;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
