package com.insoul.copartner.dao.criteria;


public class DemandCriteria extends PaginationCriteria {

    private Long userId;

    private Integer type;

    private String status[];
    
    private String projectName;// 项目名称
    
    private String content;// 融资/融智 要求

    public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

}
