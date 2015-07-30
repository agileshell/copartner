package com.insoul.copartner.vo.request;

import java.io.Serializable;

public class ProjectListRequest extends PaginationRequest implements Serializable {

    private static final long serialVersionUID = -295399433801136166L;

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
