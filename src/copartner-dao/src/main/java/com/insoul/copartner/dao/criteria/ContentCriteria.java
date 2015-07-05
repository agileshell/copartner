package com.insoul.copartner.dao.criteria;

public class ContentCriteria extends PaginationCriteria {
	
	private Long id;
	
	private String title;

    private Integer type;

    private String[] status;

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

}
