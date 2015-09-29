package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 政策解读
 */
@Entity
@Table(name = "content", catalog = "copartner")
public class Content extends BaseEntity {

    private static final long serialVersionUID = -24101514121358706L;

    @Column(name = "title", nullable = false)
    private String title;// 标题

    @Column(name = "synopsis", nullable = false)
    private String synopsis;// 摘要,显示列表使用

    @Column(name = "cover_img")
    private String coverImg;// 封皮

    @Lob
    @Column(name = "article", nullable = false, columnDefinition = "TEXT")
    private String article;// 内容

    @Column(name = "status", nullable = false)
    private String status;// 状态 active,inactive,deleted

    @Column(name = "clicks", nullable = false)
    private Long clicks = 0L;// 浏览次数

    @Column(name = "admin_user_id", nullable = false)
    private Long adminUserId;// 创建人，后台admin用户的ID

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((adminUserId == null) ? 0 : adminUserId.hashCode());
        result = prime * result + ((article == null) ? 0 : article.hashCode());
        result = prime * result + ((clicks == null) ? 0 : clicks.hashCode());
        result = prime * result + ((coverImg == null) ? 0 : coverImg.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((synopsis == null) ? 0 : synopsis.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Content other = (Content) obj;
        if (adminUserId == null) {
            if (other.adminUserId != null)
                return false;
        } else if (!adminUserId.equals(other.adminUserId))
            return false;
        if (article == null) {
            if (other.article != null)
                return false;
        } else if (!article.equals(other.article))
            return false;
        if (clicks == null) {
            if (other.clicks != null)
                return false;
        } else if (!clicks.equals(other.clicks))
            return false;
        if (coverImg == null) {
            if (other.coverImg != null)
                return false;
        } else if (!coverImg.equals(other.coverImg))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (synopsis == null) {
            if (other.synopsis != null)
                return false;
        } else if (!synopsis.equals(other.synopsis))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

}
