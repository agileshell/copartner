package com.insoul.copartner.constant;

public enum UserStatus {

    ACTIVE("active"),

    BANNED("banned"),

    DELETED("deleted");

    private UserStatus(final String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
