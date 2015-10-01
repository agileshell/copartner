package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 创业大赛
 */
@Entity
@Table(name = "contest", catalog = "copartner")
public class Contest extends BaseEntity {

    private static final long serialVersionUID = -24101514121358706L;

    @Column(name = "title", nullable = false)
    private String title;// 大赛标题

    @Lob
    @Column(name = "introduction", nullable = false, columnDefinition = "TEXT")
    private String introduction;// 大赛简介

    @Column(name = "cover_img")
    private String coverImg;// 封皮

    @Lob
    @Column(name = "rules", nullable = false, columnDefinition = "TEXT")
    private String rules;// 大赛规则

    @Lob
    @Column(name = "registration", nullable = false, columnDefinition = "TEXT")
    private String registration;// 报名信息

    @Column(name = "status", nullable = false)
    private String status;// 状态 active,inactive,deleted

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((coverImg == null) ? 0 : coverImg.hashCode());
        result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
        result = prime * result + ((registration == null) ? 0 : registration.hashCode());
        result = prime * result + ((rules == null) ? 0 : rules.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        Contest other = (Contest) obj;
        if (coverImg == null) {
            if (other.coverImg != null)
                return false;
        } else if (!coverImg.equals(other.coverImg))
            return false;
        if (introduction == null) {
            if (other.introduction != null)
                return false;
        } else if (!introduction.equals(other.introduction))
            return false;
        if (registration == null) {
            if (other.registration != null)
                return false;
        } else if (!registration.equals(other.registration))
            return false;
        if (rules == null) {
            if (other.rules != null)
                return false;
        } else if (!rules.equals(other.rules))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }
}