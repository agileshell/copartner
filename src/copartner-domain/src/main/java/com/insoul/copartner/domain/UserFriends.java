package com.insoul.copartner.domain;

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
@Table(name = "user_friends", catalog = "copartner")
@NamedQueries({
        @NamedQuery(name = "UserFriends.getByIds", query = "FROM UserFriends WHERE id.friendId = :friendId AND id.userId = :userId"),
        @NamedQuery(name = "UserFriends.getByUserId", query = "FROM UserFriends WHERE id.userId = :userId and isPassed = :isPassed") })
public class UserFriends {

    @EmbeddedId
    private UserFriendsId id;

    @Column(name = "is_passed", nullable = false)
    private Boolean isPassed = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;

    public UserFriends() {
    }

    public UserFriendsId getId() {
        return id;
    }

    public void setId(UserFriendsId id) {
        this.id = id;
    }

    public Boolean getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Boolean isPassed) {
        this.isPassed = isPassed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isPassed == null) ? 0 : isPassed.hashCode());
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
        UserFriends other = (UserFriends) obj;
        if (created == null) {
            if (other.created != null)
                return false;
        } else if (!created.equals(other.created))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isPassed == null) {
            if (other.isPassed != null)
                return false;
        } else if (!isPassed.equals(other.isPassed))
            return false;
        return true;
    }

}
