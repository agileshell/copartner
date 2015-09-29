package com.insoul.copartner.dao.criteria;

import java.util.Arrays;
import java.util.Date;

public class ContentCriteria extends PaginationCriteria {

    private Long id;

    private String title;

    private String[] status;

    private Date from;

    private Date to;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ContentCriteria [id=" + id + ", title=" + title + ", status="
                + Arrays.toString(status) + ", from=" + from + ", to=" + to + "]";
    }

}
