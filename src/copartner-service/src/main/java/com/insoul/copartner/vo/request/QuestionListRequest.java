package com.insoul.copartner.vo.request;

public class QuestionListRequest extends PaginationRequest {

    private static final long serialVersionUID = -5462334948594145892L;

    private String keyword;

    private Long categoryId;

    private Long from;

    private Long to;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
