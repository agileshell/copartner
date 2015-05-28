package com.insoul.copartner.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user", catalog = "copartner")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "FROM User u WHERE u.email = :email AND u.email <> '' AND u.email IS NOT NULL"),
        @NamedQuery(name = "User.findByMobile", query = "FROM User u WHERE u.mobile = :mobile AND u.mobile <> '' AND u.mobile IS NOT NULL") })
public class User extends BaseEntity {

    private static final long serialVersionUID = 6718859190782978249L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "mobile", unique = true)
    private String mobile;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "gender", nullable = false)
    private String gender = "F";

    @Column(name = "age", nullable = false)
    private String age = "0";

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "is_email_verified", nullable = false)
    private Boolean isEmailVerified = false;

    @Column(name = "is_mobile_verified", nullable = false)
    private Boolean isMobileVerified = false;

    @Column(name = "client_ip", nullable = false)
    private Long clientIp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "last_ip", nullable = false)
    private Long lastIp = 0L;

    @Column(name = "startup_status_id")
    private Long StartupStatusId;

    @Column(name = "startup_role_id")
    private Long startupRoleId;

    @Column(name = "domains")
    private String domains;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((StartupStatusId == null) ? 0 : StartupStatusId.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
        result = prime * result + ((clientIp == null) ? 0 : clientIp.hashCode());
        result = prime * result + ((domains == null) ? 0 : domains.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
        result = prime * result + ((isEmailVerified == null) ? 0 : isEmailVerified.hashCode());
        result = prime * result + ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
        result = prime * result + ((lastIp == null) ? 0 : lastIp.hashCode());
        result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
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
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (introduction == null) {
            if (other.introduction != null)
                return false;
        } else if (!introduction.equals(other.introduction))
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
