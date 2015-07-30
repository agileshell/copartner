package com.insoul.copartner.dao.criteria;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年7月30日 下午6:36:34
 */
public class TeamSizeCriteria extends PaginationCriteria {
	
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