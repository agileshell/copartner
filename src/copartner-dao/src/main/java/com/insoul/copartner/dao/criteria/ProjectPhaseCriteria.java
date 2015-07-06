package com.insoul.copartner.dao.criteria;

public class ProjectPhaseCriteria extends PaginationCriteria {
	
	private Long id;

	private String name;

	private Boolean listed;

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

	public Boolean getListed() {
		return listed;
	}

	public void setListed(Boolean listed) {
		this.listed = listed;
	}
}