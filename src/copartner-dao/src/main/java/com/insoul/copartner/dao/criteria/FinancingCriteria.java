package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class FinancingCriteria extends PaginationCriteria {

    private Long userId;

    private String projectName;

    private Long financingPhaseId;

    private String status[];

    private Date from;

    private Date to;

    private Long projectId;// 关联的项目编号

    private byte beused = 2;// 是否被使用 0:未使用 1:已使用

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public byte getBeused() {
        return beused;
    }

    public void setBeused(byte beused) {
        this.beused = beused;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getFinancingPhaseId() {
        return financingPhaseId;
    }

    public void setFinancingPhaseId(Long financingPhaseId) {
        this.financingPhaseId = financingPhaseId;
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
