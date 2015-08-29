package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课程
 */
@Entity
@Table(name = "course", catalog = "copartner")
public class Course extends BaseEntity {

    private static final long serialVersionUID = 3167044761663331781L;

    @Column(name = "name", nullable = false)
    private String name;// 课程名称

    @Column(name = "speaker", nullable = false)
    private String speaker;// 主讲人

    @Column(name = "synopsis", nullable = false)
    private String synopsis;// 摘要,显示列表使用

    @Column(name = "cover_img")
    private String coverImg;// 封皮

    @Column(name = "time", nullable = false)
    private Integer time;// 时长, 单位:分钟

    @Column(name = "status", nullable = false)
    private String status;// 状态 active,inactive,deleted

    @Column(name = "clicks", nullable = false)
    private Long clicks = 0L;// 浏览次数

    @Column(name = "user_id", nullable = false)
    private Long userId;// 上传者

    @Column(name = "is_free", nullable = false)
    private Boolean isFree = true; // 是否免费

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((clicks == null) ? 0 : clicks.hashCode());
        result = prime * result + ((coverImg == null) ? 0 : coverImg.hashCode());
        result = prime * result + ((isFree == null) ? 0 : isFree.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((speaker == null) ? 0 : speaker.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((synopsis == null) ? 0 : synopsis.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        Course other = (Course) obj;
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
        if (isFree == null) {
            if (other.isFree != null)
                return false;
        } else if (!isFree.equals(other.isFree))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (speaker == null) {
            if (other.speaker != null)
                return false;
        } else if (!speaker.equals(other.speaker))
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
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
