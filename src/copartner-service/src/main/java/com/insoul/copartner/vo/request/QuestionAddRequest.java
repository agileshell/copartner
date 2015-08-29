package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class QuestionAddRequest implements Serializable {

    private static final long serialVersionUID = 4007762726328402559L;

    @NotBlank
    @StringLength(max = 50)
    private String title;// 标题

    @NotNull
    private Long categoryId;// 问题类别

    @NotBlank
    @StringLength(max = 200)
    private String content;// 内容

    @NotNull
    private Long tutorId;// 导师

    @NotNull
    private Integer permission = 1;// 权限, 1:全体可见, 2:仅被提问的导师可见

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

}
