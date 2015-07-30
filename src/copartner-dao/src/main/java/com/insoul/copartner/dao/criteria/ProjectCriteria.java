package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class ProjectCriteria extends PaginationCriteria {

    private Long userId;

    private String name;

    private Long projectPhaseId;

    private String status[];

    private Date from;

    private Date to;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectPhaseId() {
        return projectPhaseId;
    }

    public void setProjectPhaseId(Long projectPhaseId) {
        this.projectPhaseId = projectPhaseId;
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
