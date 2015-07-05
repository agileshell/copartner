package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;

public class ContentDetailVO implements Serializable {

    private static final long serialVersionUID = -573224895564407516L;

    private Long contentId;

    private Integer type;// 1 政策解读, 2 公共资源

    private String title;// 标题

    private String synopsis;// 摘要,显示列表使用

    private String coverImg;// 封皮

    private String article;// 内容

    private Long clicks = 0L;// 浏览次数

    private Date created;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

}
