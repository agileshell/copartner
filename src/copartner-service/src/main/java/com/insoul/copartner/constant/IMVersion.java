package com.insoul.copartner.constant;

public enum IMVersion {

    VERSION_NULL((byte) 0, "无效版本"),

    VERSION_1_0_0((byte) 1, "version-1.0.0");

    private final byte id;

    private final String name;

    private IMVersion(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public static IMVersion parse(byte id) {
        for (IMVersion v : values()) {
            if (v.id == id) {
                return v;
            }
        }
        return VERSION_NULL;
    }

    public byte getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}