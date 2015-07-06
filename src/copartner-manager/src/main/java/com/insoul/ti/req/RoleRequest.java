package com.insoul.ti.req;

import javax.validation.constraints.NotNull;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class RoleRequest {

	@NotNull
	private String name;

	private Integer listed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getListed() {
		return listed;
	}

	public void setListed(Integer listed) {
		this.listed = listed;
	}
}