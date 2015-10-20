package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class FavouriteCriteria extends PaginationCriteria {

    private Long userId;

    private Date from;

    private Date to;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}
