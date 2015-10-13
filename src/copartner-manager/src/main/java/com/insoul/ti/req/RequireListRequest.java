package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class RequireListRequest extends PageRequest {
	
    private Long userId;

    private Integer type;//// 1加入团队， 2寻求搭档， 3寻求融资， 4寻求融智，5投资项目

	private String status;

	@Override
	protected RequireListRequest Q() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (type != null && type > 0) {
			sb.append("type").append("=").append(type);
			appended = true;
		}
		if (userId != null && userId > 0L) {
			if (appended) {
				sb.append("&");
			}
			sb.append("userId").append("=").append(userId);
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

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}