package com.insoul.copartner.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 管理员
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年7月27日 下午1:31:23
 */
@Entity
@Table(name = "co_admin", catalog = "copartner")
@NamedQueries({
    @NamedQuery(name = "Admin.queryAdmin", query = "FROM Admin WHERE loginName = :loginName") 
    })
public class Admin extends BaseEntity {

	private static final long serialVersionUID = 6718859190782978249L;

	@Column(name = "login_name", nullable = false, unique = true)
	private String loginName;// 姓名

	@Column(name = "name")
	private String name;

	@Column(name = "password", nullable = false)
	private String password;// 密码

	@Column(name = "status", nullable = false)
	private Byte status = 1;// 状态

    @Column(name = "permission", nullable = false)
	private String permission;// 系统管理权限 1:超级管理员 2:管理员 3:一般用户

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Date lastLogin;// 最后登录时间

	@Column(name = "last_ip", nullable = false)
	private Long lastIp = 0L;// 最后登录时所在的ip

	public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Long getLastIp() {
		return lastIp;
	}

	public void setLastIp(Long lastIp) {
		this.lastIp = lastIp;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((lastIp == null) ? 0 : lastIp.hashCode());
        result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
        result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((permission == null) ? 0 : permission.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        Admin other = (Admin) obj;
        if (lastIp == null) {
            if (other.lastIp != null)
                return false;
        } else if (!lastIp.equals(other.lastIp))
            return false;
        if (lastLogin == null) {
            if (other.lastLogin != null)
                return false;
        } else if (!lastLogin.equals(other.lastLogin))
            return false;
        if (loginName == null) {
            if (other.loginName != null)
                return false;
        } else if (!loginName.equals(other.loginName))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (permission == null) {
            if (other.permission != null)
                return false;
        } else if (!permission.equals(other.permission))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }
}