package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class RequirementCommentRequest implements Serializable {

    private static final long serialVersionUID = 3571510122031006583L;

    @NotNull
    @Min(1)
    private Long requirementId;

    @Min(1)
    private Long parentId = 0L;

    @NotBlank
    @StringLength(max = 100)
    private String content;

    private Long userId;

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
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
