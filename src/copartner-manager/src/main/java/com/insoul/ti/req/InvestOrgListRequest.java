package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class InvestOrgListRequest extends PageRequest {
	
	private String name;// 名称

	@Override
	protected InvestOrgListRequest Q() {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(name)) {
			sb.append("name").append("=").append(name);
		}
		getQuery().setQueryString(sb.toString());
		return this;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}