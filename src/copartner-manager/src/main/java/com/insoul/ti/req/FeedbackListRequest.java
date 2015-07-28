package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class FeedbackListRequest extends PageRequest {

	private Long userId;

	private String text;

	@Override
	protected FeedbackListRequest Q() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (userId != null && userId > 0L) {
			sb.append("userId").append("=").append(userId);
			appended = true;
		}
		if (StringUtils.isNotBlank(text)) {
			if (appended) {
				sb.append("&");
			}
			sb.append("text").append("=").append(text);
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}