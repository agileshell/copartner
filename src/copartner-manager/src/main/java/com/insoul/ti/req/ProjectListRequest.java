package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class ProjectListRequest extends PageRequest {

	private static final String ACTIVE = "active";

	private Long id;

	private String name = StringUtils.EMPTY;

	private String content = StringUtils.EMPTY;// 实施条件

	 private String status = ACTIVE;

	@Override
	protected ProjectListRequest Q() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (StringUtils.isNotBlank(name)) {
			sb.append("name").append("=").append(name);
			appended = true;
		}
		if (StringUtils.isNotBlank(content)) {
			if (appended) {
				sb.append("&");
			}
			sb.append("content").append("=").append(content);
			appended = true;
		}
		if (StringUtils.isNotBlank(status)) {
			if (appended) {
				sb.append("&");
			}
			sb.append("status").append("=").append(status);
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getStatus() {
		if (status == null || status.length() <= 0) {
			status = ACTIVE;
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}