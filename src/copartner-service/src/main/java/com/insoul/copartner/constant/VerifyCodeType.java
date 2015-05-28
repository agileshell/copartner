package com.insoul.copartner.constant;

public enum VerifyCodeType {

    REGISTER("register"),

    RETRIEVEPWD("retrievePwd");

    private VerifyCodeType(final String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
