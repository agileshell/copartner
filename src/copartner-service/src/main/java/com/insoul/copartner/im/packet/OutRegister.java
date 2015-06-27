package com.insoul.copartner.im.packet;

public class OutRegister extends AbstractOutPacket {

    private static final byte ERR = 2;

    private static final byte OK = 1;

    private String party_id;

    private String nick;

    private String avatar;

    private String weixin;

    private String qq;

    private String email;

    private String telephone;

    private String phone;

    private long gmt_created;

    private byte status;

    public OutRegister ok() {
        status = OK;
        return this;
    }

    public OutRegister err() {
        status = ERR;
        return this;
    }

    public String getParty_id() {
        return party_id;
    }

    public void setParty_id(String party_id) {
        this.party_id = party_id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getGmt_created() {
        return gmt_created;
    }

    public void setGmt_created(long gmt_created) {
        this.gmt_created = gmt_created;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

}
