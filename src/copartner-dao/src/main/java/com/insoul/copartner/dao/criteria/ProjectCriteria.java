package com.insoul.copartner.dao.criteria;

public class ProjectCriteria extends PaginationCriteria {
	
	private Long id;
	
	private String name;// 项目名称
	
	private String content;// 实施条件

    private Long userId;

    private String status[];

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

}
