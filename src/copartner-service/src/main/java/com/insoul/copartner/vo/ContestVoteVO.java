package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class ContestVoteVO implements Serializable {

    private static final long serialVersionUID = -8434602445926383442L;

    private UserLeanVO votor;

    private String comment;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    public UserLeanVO getVotor() {
        return votor;
    }

    public void setVotor(UserLeanVO votor) {
        this.votor = votor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
