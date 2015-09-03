package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class QuestionListRequest extends PageRequest {
	
    private String keyword;

	private String status;

	@Override
	protected QuestionListRequest Q() {
		StringBuilder sb = new StringBuilder();
		boolean appended = false;
		if (StringUtils.isNotBlank(status)) {
			if (appended) {
				sb.append("&");
			}
			sb.append("status").append("=").append(status);
			appended = true;
		}
		if (StringUtils.isNotBlank(keyword)) {
			if (appended) {
				sb.append("&");
			}
			sb.append("keyword").append("=").append(keyword);
			appended = true;
		}
		getQuery().setQueryString(sb.toString());
		return this;
	}

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}