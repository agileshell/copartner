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
    @StringLength(max = 200)
    private String content;

    @NotNull
    @Min(1)
    @Max(3)
    private Byte type;

    private Long projectId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
