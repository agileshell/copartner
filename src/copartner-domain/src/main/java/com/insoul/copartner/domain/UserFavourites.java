package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user_favourites", catalog = "copartner")
@NamedQueries({
        @NamedQuery(
                name = "UserFavourites.getByUserIdAndEntity",
                query = "FROM UserFavourites WHERE userId = :userId AND entityId = :entityId AND entityType = :entityType"),
        @NamedQuery(name = "UserFavourites.findByUserId",
                query = "FROM UserFavourites WHERE userId = :userId ORDER BY created DESC")})
public class UserFavourites extends BaseEntity {

    private static final long serialVersionUID = 4812280520599736187L;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "entity_type", nullable = false)
    private String entityType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
        result = prime * result + ((entityType == null) ? 0 : entityType.hashCode());
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
        UserFavourites other = (UserFavourites) obj;
        if (entityId == null) {
            if (other.entityId != null)
                return false;
        } else if (!entityId.equals(other.entityId))
            return false;
        if (entityType == null) {
            if (other.entityType != null)
                return false;
        } else if (!entityType.equals(other.entityType))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
