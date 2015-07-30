package com.insoul.copartner.dao.criteria;

public class ProjectCommentCriteria extends PaginationCriteria {

    private Long projectId;
    
    private String status;// 状态 active 可用，inactive不可用

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
