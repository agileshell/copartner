package com.insoul.copartner.vo.request;

public class DemandListRequest extends PaginationRequest {

    private static final long serialVersionUID = -7650378463719813280L;

    private Long userId;

    private Long from;

    private Long to;

    private String keyword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
