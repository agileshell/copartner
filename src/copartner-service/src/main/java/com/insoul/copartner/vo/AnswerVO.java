package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class AnswerVO implements Serializable {

    private static final long serialVersionUID = -8096082619458850209L;

    private UserLeanVO answeror; // 回答者

    private String content;// 回答内容

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    public UserLeanVO getAnsweror() {
        return answeror;
    }

    public void setAnsweror(UserLeanVO answeror) {
        this.answeror = answeror;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
