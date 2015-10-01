package com.insoul.copartner.vo;

import java.io.Serializable;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月1日 下午11:21:08
 */
public class ContestEntryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String name;// 参赛项目名称
    
    private Long contestId;// 参与的大赛ID

    private String userName;// 参赛者姓名

    private String contact;// 参赛者联系方式

    private String coverImg;// 封皮

    private Long praise;// 赞的次数

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
