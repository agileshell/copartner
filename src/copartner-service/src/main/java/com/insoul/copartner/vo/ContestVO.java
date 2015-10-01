package com.insoul.copartner.vo;

import java.io.Serializable;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月1日 下午11:06:28
 */
public class ContestVO implements Serializable {

    private static final long serialVersionUID = -5522476659698148139L;

    private Long id;
    
    private String title;// 大赛标题

    private String introduction;// 大赛简介

    private String coverImg;// 封皮

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
}