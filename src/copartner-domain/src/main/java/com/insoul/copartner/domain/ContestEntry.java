package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 参赛项目
 */
@Entity
@Table(name = "contest", catalog = "copartner")
public class ContestEntry extends BaseEntity {

    private static final long serialVersionUID = -24101514121358706L;

    @Column(name = "name", nullable = false)
    private String name;// 参赛项目名称

    @Column(name = "user_name", nullable = false)
    private String userName;// 参赛者姓名

    @Column(name = "contest_id", nullable = false)
    private Long contestId;// 参与的大赛ID

    @Column(name = "contact")
    private String contact;// 参赛者联系方式

    @Column(name = "cover_img")
    private String coverImg;// 封皮
    
    @Column(name = "praise")
    private Long praise;// 赞的次数
    
    @Column(name = "introduction", nullable = false)
    private String introduction;// 项目简介

    @Column(name = "status", nullable = false)
    private String status;// 状态 active, inactive, deleted

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Long getPraise() {
        return praise;
    }

    public void setPraise(Long praise) {
        this.praise = praise;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((coverImg == null) ? 0 : coverImg.hashCode());
        result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((praise == null) ? 0 : praise.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        ContestEntry other = (ContestEntry) obj;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (praise == null) {
            if (other.praise != null)
                return false;
        } else if (!praise.equals(other.praise))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
}