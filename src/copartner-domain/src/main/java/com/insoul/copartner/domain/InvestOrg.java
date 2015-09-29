package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 投资机构
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 上午11:16:15
 */
@Entity
@Table(name = "invest_organiza", catalog = "copartner")
public class InvestOrg extends BaseEntity {

    private static final long serialVersionUID = 3278438251521879153L;

    @Column(name = "name", nullable = false)
    private String name;// 名称
    
    @Column(name = "specials", nullable = false)
    private String specials;// 特色
    
    @Column(name = "hardware", nullable = false)
    private String hardware;// 硬件
    
    @Column(name = "logo")
    private String logo;
    
    /**
     * 简介
     */
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecials() {
        return specials;
    }

    public void setSpecials(String specials) {
        this.specials = specials;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}