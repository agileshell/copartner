package com.insoul.copartner.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_account_confirmation", catalog = "copartner")
@NamedQueries({ @NamedQuery(name = "UserAccountConfirmation.findUserAccountConfirmation", query = "FROM UserAccountConfirmation WHERE id.accountType = :accountType AND id.account = :account AND id.code = :code AND isConfirmed = false AND isExpired = false") })
public class UserAccountConfirmation implements Serializable {

    private static final long serialVersionUID = 3067667207265292468L;

    @EmbeddedId
    private UserAccountConfirmationId id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed = false;

    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date confirmed;

    public UserAccountConfirmationId getId() {
        return id;
    }

    public void setId(UserAccountConfirmationId id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Date confirmed) {
        this.confirmed = confirmed;
    }

}
