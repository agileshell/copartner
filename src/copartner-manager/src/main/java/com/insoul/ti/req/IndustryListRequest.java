package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class IndustryListRequest extends PageRequest {

	private Long id;

	private String name;

	private Integer listed = 1;

	@Override
	protected IndustryListRequest Q() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (StringUtils.isNotBlank(name)) {
			sb.append("name").append("=").append(name);
			appended = true;
		}
		if (listed != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("listed").append("=").append(listed);
			appended = true;
		}
		if (id != null && id > 0L) {
			if (appended) {
				sb.append("&");
			}
			sb.append("id").append("=").append(id);
			appended = true;
		}
		getQuery().setQueryString(sb.toString());
		return this;
	}

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

	public Integer getListed() {
		return listed;
	}

	public void setListed(Integer listed) {
		this.listed = listed;
	}
}