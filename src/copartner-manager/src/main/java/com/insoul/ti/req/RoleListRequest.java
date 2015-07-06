package com.insoul.ti.req;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class RoleListRequest extends PageRequest {

	private Long id;

	private String name;

	private Integer listed;

	@Override
	protected RoleListRequest Q() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (name != null) {
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
		if (id != null) {
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