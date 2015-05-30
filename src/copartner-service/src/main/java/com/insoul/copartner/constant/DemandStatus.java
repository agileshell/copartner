package com.insoul.copartner.constant;

public enum DemandStatus {
    ACTIVE("active"),

    BANNED("banned"),

    DELETED("deleted");

    private DemandStatus(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
