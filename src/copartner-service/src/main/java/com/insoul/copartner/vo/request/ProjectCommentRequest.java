package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class ProjectCommentRequest implements Serializable {

    private static final long serialVersionUID = 6970769992081294479L;

    @NotNull
    @Min(1)
    private Long projectId;

    @Min(1)
    private Long parentId = 0L;

    @NotBlank
    @StringLength(max = 100)
    private String content;

    private Long userId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
