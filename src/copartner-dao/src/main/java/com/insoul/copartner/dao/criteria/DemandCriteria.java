package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class DemandCriteria extends PaginationCriteria {

    private Long userId;

    private String status[];

    private String projectName;

    private Date from;

    private Date to;
    
    private int beused;// 是否被使用 0:未使用 1:已使用

    public int getBeused() {
        return beused;
    }

    public void setBeused(int beused) {
        this.beused = beused;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}
