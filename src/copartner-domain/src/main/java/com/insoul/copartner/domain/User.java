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
 * 用户
 */
@Entity
@Table(name = "user", catalog = "copartner")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "FROM User u WHERE u.email = :email AND u.email <> '' AND u.email IS NOT NULL"),
        @NamedQuery(name = "User.findByMobile", query = "FROM User u WHERE u.mobile = :mobile AND u.mobile <> '' AND u.mobile IS NOT NULL"),
        @NamedQuery(name = "User.findByIds", query = "FROM User WHERE id IN(:userIds)") })
public class User extends BaseEntity {

    private static final long serialVersionUID = 6718859190782978249L;

    @Column(name = "role_id", nullable = false)
    private Long roleId = 1L;// 1:创业者, 2:投资人, 3:导师

    @Column(name = "level", nullable = false)
    private Integer level = 0;// VIP等级

    @Column(name = "points")
    private Long points = 0L;// 积分

    @Column(name = "name", nullable = false)
    private String name;// 姓名

    @Column(name = "email", unique = true)
    private String email;// 邮箱

    @Column(name = "mobile", unique = true)
    private String mobile;// 电话

    @Column(name = "password", nullable = false)
    private String password;// 密码

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "status", nullable = false)
    private String status = "active";// 状态

    @Column(name = "avatar")
    private String avatar;// 头像

    @Column(name = "location_id")
    private Long locationId;// 所在地区

    @Column(name = "full_location")
    private String fullLocation;// 地区缓存

    @Column(name = "gender", nullable = false)
    private String gender = "F";// 性别 F男 M女

    @Column(name = "age", nullable = false)
    private String age = "0";// 年龄

    @Column(name = "introduction")
    private String introduction;// 简介

    @Column(name = "is_email_verified", nullable = false)
    private Boolean isEmailVerified = false;// 邮箱是否已验证

    @Column(name = "is_mobile_verified", nullable = false)
    private Boolean isMobileVerified = false;// 电话是否已验证

    @Column(name = "client_ip", nullable = false)
    private Long clientIp;// 注册时所在的ip

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;// 最后登录时间

    @Column(name = "last_ip", nullable = false)
    private Long lastIp = 0L;// 最后登录时所在的ip

    @Column(name = "startup_status_id")
    private Long StartupStatusId;// 目前状况

    @Column(name = "startup_role_id")
    private Long startupRoleId;// 角色

    @Column(name = "domains")
    private String domains;// 行业

    @Column(name = "im_id")
    private Long imId;// 聊天ID

    @Column(name = "id_number")
    private String idNumber;// 身份证号

    @Column(name = "id_picture")
    private String idPicture;// 身份图片

    @Column(name = "authenticated")
    private Boolean authenticated = false;// 是否已认证

    @Column(name = "authentication_info")
    private String authenticationInfo;// 认证说明

    @Column(name = "profession_id")
    private Long professionId = 0L;// 1:学术型 2:实业型

    @Column(name = "investment_org")
    private String investmentOrg;// 投资机构

    @Column(name = "investment_style")
    private String investmentStyle;// 投资风格

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getIsMobileVerified() {
        return isMobileVerified;
    }

    public void setIsMobileVerified(Boolean isMobileVerified) {
        this.isMobileVerified = isMobileVerified;
    }

    public Long getClientIp() {
        return clientIp;
    }

    public void setClientIp(Long clientIp) {
        this.clientIp = clientIp;
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

    public Long getStartupStatusId() {
        return StartupStatusId;
    }

    public void setStartupStatusId(Long startupStatusId) {
        StartupStatusId = startupStatusId;
    }

    public Long getStartupRoleId() {
        return startupRoleId;
    }

    public void setStartupRoleId(Long startupRoleId) {
        this.startupRoleId = startupRoleId;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public Long getImId() {
        return imId;
    }

    public void setImId(Long imId) {
        this.imId = imId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(String idPicture) {
        this.idPicture = idPicture;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(String authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public String getInvestmentOrg() {
        return investmentOrg;
    }

    public void setInvestmentOrg(String investmentOrg) {
        this.investmentOrg = investmentOrg;
    }

    public String getInvestmentStyle() {
        return investmentStyle;
    }

    public void setInvestmentStyle(String investmentStyle) {
        this.investmentStyle = investmentStyle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((StartupStatusId == null) ? 0 : StartupStatusId.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((authenticated == null) ? 0 : authenticated.hashCode());
        result = prime * result + ((authenticationInfo == null) ? 0 : authenticationInfo.hashCode());
        result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
        result = prime * result + ((clientIp == null) ? 0 : clientIp.hashCode());
        result = prime * result + ((domains == null) ? 0 : domains.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fullLocation == null) ? 0 : fullLocation.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
        result = prime * result + ((idPicture == null) ? 0 : idPicture.hashCode());
        result = prime * result + ((imId == null) ? 0 : imId.hashCode());
        result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
        result = prime * result + ((investmentOrg == null) ? 0 : investmentOrg.hashCode());
        result = prime * result + ((investmentStyle == null) ? 0 : investmentStyle.hashCode());
        result = prime * result + ((isEmailVerified == null) ? 0 : isEmailVerified.hashCode());
        result = prime * result + ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
        result = prime * result + ((lastIp == null) ? 0 : lastIp.hashCode());
        result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((points == null) ? 0 : points.hashCode());
        result = prime * result + ((professionId == null) ? 0 : professionId.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((salt == null) ? 0 : salt.hashCode());
        result = prime * result + ((startupRoleId == null) ? 0 : startupRoleId.hashCode());
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
        User other = (User) obj;
        if (StartupStatusId == null) {
            if (other.StartupStatusId != null)
                return false;
        } else if (!StartupStatusId.equals(other.StartupStatusId))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (authenticated == null) {
            if (other.authenticated != null)
                return false;
        } else if (!authenticated.equals(other.authenticated))
            return false;
        if (authenticationInfo == null) {
            if (other.authenticationInfo != null)
                return false;
        } else if (!authenticationInfo.equals(other.authenticationInfo))
            return false;
        if (avatar == null) {
            if (other.avatar != null)
                return false;
        } else if (!avatar.equals(other.avatar))
            return false;
        if (clientIp == null) {
            if (other.clientIp != null)
                return false;
        } else if (!clientIp.equals(other.clientIp))
            return false;
        if (domains == null) {
            if (other.domains != null)
                return false;
        } else if (!domains.equals(other.domains))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (fullLocation == null) {
            if (other.fullLocation != null)
                return false;
        } else if (!fullLocation.equals(other.fullLocation))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (idNumber == null) {
            if (other.idNumber != null)
                return false;
        } else if (!idNumber.equals(other.idNumber))
            return false;
        if (idPicture == null) {
            if (other.idPicture != null)
                return false;
        } else if (!idPicture.equals(other.idPicture))
            return false;
        if (imId == null) {
            if (other.imId != null)
                return false;
        } else if (!imId.equals(other.imId))
            return false;
        if (introduction == null) {
            if (other.introduction != null)
                return false;
        } else if (!introduction.equals(other.introduction))
            return false;
        if (investmentOrg == null) {
            if (other.investmentOrg != null)
                return false;
        } else if (!investmentOrg.equals(other.investmentOrg))
            return false;
        if (investmentStyle == null) {
            if (other.investmentStyle != null)
                return false;
        } else if (!investmentStyle.equals(other.investmentStyle))
            return false;
        if (isEmailVerified == null) {
            if (other.isEmailVerified != null)
                return false;
        } else if (!isEmailVerified.equals(other.isEmailVerified))
            return false;
        if (isMobileVerified == null) {
            if (other.isMobileVerified != null)
                return false;
        } else if (!isMobileVerified.equals(other.isMobileVerified))
            return false;
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
        if (level == null) {
            if (other.level != null)
                return false;
        } else if (!level.equals(other.level))
            return false;
        if (locationId == null) {
            if (other.locationId != null)
                return false;
        } else if (!locationId.equals(other.locationId))
            return false;
        if (mobile == null) {
            if (other.mobile != null)
                return false;
        } else if (!mobile.equals(other.mobile))
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
        if (points == null) {
            if (other.points != null)
                return false;
        } else if (!points.equals(other.points))
            return false;
        if (professionId == null) {
            if (other.professionId != null)
                return false;
        } else if (!professionId.equals(other.professionId))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (salt == null) {
            if (other.salt != null)
                return false;
        } else if (!salt.equals(other.salt))
            return false;
        if (startupRoleId == null) {
            if (other.startupRoleId != null)
                return false;
        } else if (!startupRoleId.equals(other.startupRoleId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

}
