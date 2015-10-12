package com.insoul.copartner.vo.request;

public class RequirementListRequest extends PaginationRequest {

    private static final long serialVersionUID = 4700698612684158644L;

    private Long userId;

    private Long from;

    private Long to;

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

}
