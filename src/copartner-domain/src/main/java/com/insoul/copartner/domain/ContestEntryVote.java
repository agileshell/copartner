package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "contest_entry_vote", catalog = "copartner")
@NamedQueries({
        @NamedQuery(name = "ContestEntryVote.getByContestEntryAndVotor", query = "FROM ContestEntryVote WHERE contestEntryId = :contestEntryId AND votorId = :votorId") })
public class ContestEntryVote extends BaseEntity {

    private static final long serialVersionUID = 7939215418097887632L;

    @Column(name = "contest_entry_id", nullable = false)
    private Long contestEntryId;

    @Column(name = "votor_id", nullable = false)
    private Long votorId;

    private String comment;

    public Long getContestEntryId() {
        return contestEntryId;
    }

    public void setContestEntryId(Long contestEntryId) {
        this.contestEntryId = contestEntryId;
    }

    public Long getVotorId() {
        return votorId;
    }

    public void setVotorId(Long votorId) {
        this.votorId = votorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + ((contestEntryId == null) ? 0 : contestEntryId.hashCode());
        result = prime * result + ((votorId == null) ? 0 : votorId.hashCode());
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
        ContestEntryVote other = (ContestEntryVote) obj;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        if (contestEntryId == null) {
            if (other.contestEntryId != null)
                return false;
        } else if (!contestEntryId.equals(other.contestEntryId))
            return false;
        if (votorId == null) {
            if (other.votorId != null)
                return false;
        } else if (!votorId.equals(other.votorId))
            return false;
        return true;
    }

}
