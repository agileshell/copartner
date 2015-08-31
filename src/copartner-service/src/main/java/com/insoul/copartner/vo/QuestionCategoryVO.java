package com.insoul.copartner.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionCategoryVO implements Serializable {

    private static final long serialVersionUID = -8896243786919748932L;

    @JsonProperty("value")
    private Long id;

    @JsonProperty("text")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
