package com.insoul.copartner.im.packet;

public class AbstractOutPacket {

    private long mid;

    private byte type;

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

}
