package com.insoul.copartner.im.packet;

public abstract class AbstractInPacket {

    private byte type;

    public AbstractInPacket(byte type) {
        super();
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

}