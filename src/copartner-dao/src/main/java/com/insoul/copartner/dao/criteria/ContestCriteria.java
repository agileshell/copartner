package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class ContestCriteria extends PaginationCriteria {

    private String title;// 大赛标题

    private Date from;

    private Date to;

    private String status;// 状态 active,inactive,deleted

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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