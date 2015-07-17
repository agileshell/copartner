package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class DemandAddRequest implements Serializable {

    private static final long serialVersionUID = -3145254501267573803L;

    @NotBlank
    @StringLength(max = 50)
    private String ProjectName;// 项目名称

    @NotNull
    @Min(1)
    @Max(2)
    private Integer type;

    @NotNull
    private Long teamSizeId;// 团队规模

    @NotNull
    private Long projectPhaseId;// 阶段

    @NotNull
    private Long locationId;// 所属地区

    @NotNull
    private Long industryDomainId;// 行业

    @NotBlank
    @StringLength(max = 200)
    private String content;// 实施条件

    @StringLength(max = 200)
    private String advantage;// 优势

    @StringLength(max = 30)
    private String contactPerson;// 联系人

    @StringLength(max = 30)
    private String contact;// 联系方式

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTeamSizeId() {
        return teamSizeId;
    }

    public void setTeamSizeId(Long teamSizeId) {
        this.teamSizeId = teamSizeId;
    }

    public Long getProjectPhaseId() {
        return projectPhaseId;
    }

    public void setProjectPhaseId(Long projectPhaseId) {
        this.projectPhaseId = projectPhaseId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getIndustryDomainId() {
        return industryDomainId;
    }

    public void setIndustryDomainId(Long industryDomainId) {
        this.industryDomainId = industryDomainId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
