package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class NewsCriteria extends PaginationCriteria {

    private String title;

    private Integer type;

    private String[] status;

    private Date from;

    private Date to;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String[] getStatus() {
        if (null == status || status.length <= 0) {
            status = new String[] { "active", "inactive" };
        }

        return status;
    }

    public void setStatus(String[] status) {
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
