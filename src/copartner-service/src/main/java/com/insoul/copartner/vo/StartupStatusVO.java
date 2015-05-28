package com.insoul.copartner.vo;

import java.io.Serializable;

public class StartupStatusVO implements Serializable {

    private static final long serialVersionUID = -8826498455444337653L;

    private Long id;

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
