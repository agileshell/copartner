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
    
    /**
     * 简介
     */
    @Column(name = "content")
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((hardware == null) ? 0 : hardware.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((specials == null) ? 0 : specials.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvestOrg other = (InvestOrg) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (hardware == null) {
            if (other.hardware != null)
                return false;
        } else if (!hardware.equals(other.hardware))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (specials == null) {
            if (other.specials != null)
                return false;
        } else if (!specials.equals(other.specials))
            return false;
        return true;
    }
}