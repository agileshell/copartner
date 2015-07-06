package com.insoul.copartner.dao.criteria;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年7月6日 下午4:56:05
 */
public class FeedbackCriteria extends PaginationCriteria {
	
	private Long userId;

	private String text;

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