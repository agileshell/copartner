package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 目前状态
 */
@Entity
@Table(name = "startup_status", catalog = "copartner")
@NamedQueries({ @NamedQuery(name = "StartupStatus.getAllListed", query = "FROM StartupStatus WHERE isListed = true") })
public class StartupStatus extends BaseEntity {

    private static final long serialVersionUID = 47207892803933315L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_listed", nullable = false)
    private Boolean isListed;// 是否显示

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsListed() {
        return isListed;
    }

    public void setIsListed(Boolean isListed) {
        this.isListed = isListed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((isListed == null) ? 0 : isListed.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        StartupStatus other = (StartupStatus) obj;
        if (isListed == null) {
            if (other.isListed != null)
                return false;
        } else if (!isListed.equals(other.isListed))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
