package com.insoul.copartner.vo.request;

public class CourseListRequest extends PaginationRequest {

    private static final long serialVersionUID = -3251728888487776112L;

    private String keyword;

    private Boolean isFree;

    private Long from;

    private Long to;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

}
