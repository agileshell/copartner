package com.insoul.ti.req;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class ContestEntryRequest {

    @NotNull
    private String name;// 参赛项目名称

    private String userName;// 参赛者姓名

    private Long contestId;// 参与的大赛ID

    private String contact;// 参赛者联系方式

    private MultipartFile coverImg;// 封皮

    private String introduction;// 项目简介

    private String status;// 状态 active, inactive, deleted

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

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public MultipartFile getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(MultipartFile coverImg) {
        this.coverImg = coverImg;
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
}
