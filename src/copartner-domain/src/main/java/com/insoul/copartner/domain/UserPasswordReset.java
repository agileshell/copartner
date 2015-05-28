package com.insoul.copartner.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_password_reset", catalog = "copartner")
@NamedQueries({ @NamedQuery(name = "UserPasswordReset.findUserPasswordReset", query = "FROM UserPasswordReset WHERE userId = :userId AND code = :code AND isReset = false AND isExpired = false") })
public class UserPasswordReset extends BaseEntity {

    private static final long serialVersionUID = -8824243599899159679L;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_reset", nullable = false)
    private Boolean isReset = false;

    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date reset;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsReset() {
        return isReset;
    }

    public void setIsReset(Boolean isReset) {
        this.isReset = isReset;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Date getReset() {
        return reset;
    }

    public void setReset(Date reset) {
        this.reset = reset;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((isExpired == null) ? 0 : isExpired.hashCode());
        result = prime * result + ((isReset == null) ? 0 : isReset.hashCode());
        result = prime * result + ((reset == null) ? 0 : reset.hashCode());
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
        UserPasswordReset other = (UserPasswordReset) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (isExpired == null) {
            if (other.isExpired != null)
                return false;
        } else if (!isExpired.equals(other.isExpired))
            return false;
        if (isReset == null) {
            if (other.isReset != null)
                return false;
        } else if (!isReset.equals(other.isReset))
            return false;
        if (reset == null) {
            if (other.reset != null)
                return false;
        } else if (!reset.equals(other.reset))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
