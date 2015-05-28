package com.insoul.copartner.vo.request;

import java.io.Serializable;

import com.insoul.copartner.validate.constraint.StringLength;
import com.insoul.copartner.validate.constraint.StringPattern;

public class UserProfileUpdateRequest implements Serializable {

    private static final long serialVersionUID = -994521443246533850L;

    @StringLength(max = 64, min = 2)
    private String name;

    private String avatar;

    @StringPattern(regexp = "M|F")
    private String gender;

    private Long locationId;

    private String age;

    @StringLength(max = 100)
    private String introduction;

    private Long startupStatusId;

    private Long startupRoleId;

    private Long domainIds[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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

    public Long getStartupStatusId() {
        return startupStatusId;
    }

    public void setStartupStatusId(Long startupStatusId) {
        this.startupStatusId = startupStatusId;
    }

    public Long getStartupRoleId() {
        return startupRoleId;
    }

    public void setStartupRoleId(Long startupRoleId) {
        this.startupRoleId = startupRoleId;
    }

    public Long[] getDomainIds() {
        return domainIds;
    }

    public void setDomainIds(Long[] domainIds) {
        this.domainIds = domainIds;
    }

}
