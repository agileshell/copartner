package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.List;

public class ContestDetailVO implements Serializable {

    private static final long serialVersionUID = -6325269448027396783L;

    private Long id;

    private String title;// 大赛标题

    private String introduction;// 大赛简介

    private String coverImg;// 封皮

    private String registration;// 报名信息

    private boolean isRegister;// 是否报名

    private List<ContestEntryLeanVO> tutorVoteRanking;

    private List<ContestEntryLeanVO> investorVoteRanking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean isRegister) {
        this.isRegister = isRegister;
    }

    public List<ContestEntryLeanVO> getTutorVoteRanking() {
        return tutorVoteRanking;
    }

    public void setTutorVoteRanking(List<ContestEntryLeanVO> tutorVoteRanking) {
        this.tutorVoteRanking = tutorVoteRanking;
    }

    public List<ContestEntryLeanVO> getInvestorVoteRanking() {
        return investorVoteRanking;
    }

    public void setInvestorVoteRanking(List<ContestEntryLeanVO> investorVoteRanking) {
        this.investorVoteRanking = investorVoteRanking;
    }

}