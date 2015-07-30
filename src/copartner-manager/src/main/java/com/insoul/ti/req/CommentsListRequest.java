package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class CommentsListRequest extends PageRequest {
	
	private int type = 1;// 1:融资融智的评论 2:项目的评论

	private Long domainId;
    
    private String status = "active";// 状态 active 可用，inactive不可用

	@Override
	protected CommentsListRequest Q() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (type > 0) {
			sb.append("type").append("=").append(type);
			appended = true;
		} else {
			type = 1;
			sb.append("type").append("=").append(type);
			appended = true;
		}
		if (domainId != null) {
			if (appended) {
				sb.append("&");
			}
			sb.append("domainId").append("=").append(domainId);
			appended = true;
		}
		if (StringUtils.isNotBlank(status)) {
			if (appended) {
				sb.append("&");
			}
			sb.append("status").append("=").append(status);
			appended = true;
		}
		getQuery().setQueryString(sb.toString());
		return this;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}