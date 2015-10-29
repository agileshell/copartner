package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.util.CustomDateSerializer;

public class ContentDetailVO implements Serializable {

    private static final long serialVersionUID = -573224895564407516L;

    private Long contentId;

    private String title;// 标题

    private String synopsis;// 摘要,显示列表使用

    private String coverImg;// 封皮

    private String article;// 内容

    private Long clicks = 0L;// 浏览次数

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;
    
    private byte type;// 1:国家 0:地方 默认是0

    private String shareUrl;

    private boolean isliked;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.shareUrl = GlobalProperties.MOBILE_DOMAIN + "policy/" + contentId;
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

}
