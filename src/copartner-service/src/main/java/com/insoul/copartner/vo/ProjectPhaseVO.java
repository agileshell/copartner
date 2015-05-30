package com.insoul.copartner.vo;

import java.io.Serializable;

public class ProjectPhaseVO implements Serializable {

    private static final long serialVersionUID = 3821899266245164418L;

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
