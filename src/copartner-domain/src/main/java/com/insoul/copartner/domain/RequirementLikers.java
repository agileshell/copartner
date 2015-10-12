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
@Table(name = "requirement_likers", catalog = "copartner")
@NamedQueries({
        @NamedQuery(name = "RequirementLikers.getByIds",
                query = "FROM RequirementLikers WHERE id.requirementId = :requirementId AND id.userId = :userId"),
        @NamedQuery(name = "RequirementLikers.getByRequirementId",
                query = "FROM RequirementLikers WHERE id.requirementId = :requirementId ORDER BY created DESC")})
public class RequirementLikers implements Serializable {

    private static final long serialVersionUID = -3890736524712304663L;

    @EmbeddedId
    private RequirementLikersId id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;

    public RequirementLikersId getId() {
        return id;
    }

    public void setId(RequirementLikersId id) {
        this.id = id;
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
        RequirementLikers other = (RequirementLikers) obj;
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
        return true;
    }

}
