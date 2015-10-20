package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class QuestionCriteria extends PaginationCriteria {

    private String keyword;

    private Long userId;

    private Long tutorId;

    private Long categoryId;

    private Date from;

    private Date to;

    private String[] status;

    private boolean onlyOwner;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTutorId() {
        return tutorId;
    }

    public void setTutorId(Long tutorId) {
        this.tutorId = tutorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

    public boolean isOnlyOwner() {
        return onlyOwner;
    }

    public void setOnlyOwner(boolean onlyOwner) {
        this.onlyOwner = onlyOwner;
    }

}
