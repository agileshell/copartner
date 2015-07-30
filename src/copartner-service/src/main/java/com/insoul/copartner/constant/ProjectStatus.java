package com.insoul.copartner.constant;

public enum ProjectStatus {
    ACTIVE("active"),

    INACTIVE("inactive"),

    DELETED("deleted");

    private ProjectStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
