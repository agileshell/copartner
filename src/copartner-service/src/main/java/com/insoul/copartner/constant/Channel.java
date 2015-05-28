package com.insoul.copartner.constant;

public enum Channel {

    EMAIL(1),

    SMS(2);

    private Channel(final int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }
}