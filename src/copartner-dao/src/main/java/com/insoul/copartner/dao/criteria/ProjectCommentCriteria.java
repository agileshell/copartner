package com.insoul.copartner.dao.criteria;

public class ProjectCommentCriteria extends PaginationCriteria {

    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
