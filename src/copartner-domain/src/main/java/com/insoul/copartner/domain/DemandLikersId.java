package com.insoul.copartner.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DemandLikersId implements Serializable {

    private static final long serialVersionUID = -3752873220301039374L;

    @Column(name = "demand_id", nullable = false)
    private Long demandId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public DemandLikersId() {
    }

    public DemandLikersId(Long demandId, Long userId) {
        this.demandId = demandId;
        this.userId = userId;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((demandId == null) ? 0 : demandId.hashCode());
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
        DemandLikersId other = (DemandLikersId) obj;
        if (demandId == null) {
            if (other.demandId != null)
                return false;
        } else if (!demandId.equals(other.demandId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
