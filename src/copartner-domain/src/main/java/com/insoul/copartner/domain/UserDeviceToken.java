package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user_device_token")
@NamedQueries({
        @NamedQuery(name = "UserDeviceToken.deletebyUserId",
                query = "DELETE FROM UserDeviceToken WHERE userId = :userId"),
        @NamedQuery(name = "UserDeviceToken.getByUserId", query = "FROM UserDeviceToken WHERE userId = :userId"),
        @NamedQuery(name = "UserDeviceToken.getByDeviceTokenAndOs", query = "FROM UserDeviceToken WHERE deviceOs = :deviceOs AND deviceToken = :deviceToken")})
public class UserDeviceToken extends BaseEntity {

    private static final long serialVersionUID = 2789004981218060614L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "device_os", nullable = false)
    private String deviceOs;

    @Column(name = "device_token", nullable = false)
    private String deviceToken;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((deviceOs == null) ? 0 : deviceOs.hashCode());
        result = prime * result + ((deviceToken == null) ? 0 : deviceToken.hashCode());
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
        UserDeviceToken other = (UserDeviceToken) obj;
        if (deviceOs == null) {
            if (other.deviceOs != null)
                return false;
        } else if (!deviceOs.equals(other.deviceOs))
            return false;
        if (deviceToken == null) {
            if (other.deviceToken != null)
                return false;
        } else if (!deviceToken.equals(other.deviceToken))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
