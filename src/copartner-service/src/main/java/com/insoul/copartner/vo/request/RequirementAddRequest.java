package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class RequirementAddRequest implements Serializable {

    private static final long serialVersionUID = -2962523572493407772L;

    @NotNull
    private Integer type = 1;// 1加入团队， 2寻求搭档， 3寻求融资， 4寻求融智，5投资项目

    private Long projectId;// 关联项目

    @NotBlank
    @StringLength(max = 200)
    private String content;// 需求内容

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
