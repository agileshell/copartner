package com.insoul.copartner.constant;

public enum MessageType {

    REGISTER(1),

    RETRIEVE_PWD(2);

    private MessageType(final int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }
}