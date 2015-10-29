package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class ContentVO implements Serializable {

    private static final long serialVersionUID = 6489115642072650030L;

    private Long contentId;

    private String title;// 标题

    private String synopsis;// 摘要,显示列表使用

    private String coverImg;// 封皮

    private Long clicks = 0L;// 浏览次数
    
    private byte type;// 1:国家 0:地方 默认是0

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;
    
    private ServiceArchVO serviceArch;

    public ServiceArchVO getServiceArch() {
        return serviceArch;
    }

    public void setServiceArch(ServiceArchVO serviceArch) {
        this.serviceArch = serviceArch;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
