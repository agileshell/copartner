package com.insoul.copartner.im.packet;

public enum PacketType {

    IQ((byte) 1), Message((byte) 2), Presence((byte) 3), Register((byte) 4), Acknowledge((byte) 5), Revise((byte) 6);

    public final byte type;

    private PacketType(byte type) {
        this.type = type;
    }

    public static PacketType parse(byte type) {
        for (PacketType t : values()) {
            if (t.type == type) {
                return t;
            }
        }
        return null;
    }
}