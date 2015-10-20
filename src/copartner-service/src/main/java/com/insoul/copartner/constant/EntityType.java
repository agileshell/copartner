package com.insoul.copartner.constant;

public enum EntityType {
    NEWS("news"),

    CONTENT("content");

    private EntityType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
