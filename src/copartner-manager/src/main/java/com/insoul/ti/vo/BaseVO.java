package com.insoul.ti.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年7月28日 下午5:01:59
 */
public class BaseVO {

	public static final String S_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final SimpleDateFormat formatter = new SimpleDateFormat(S_YYYY_MM_DD_HH_MM_SS);
	
	private Long id;

	private Date created;

	private Date updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGmtcreated() {
		if (created == null) {
			return StringUtils.EMPTY;
		}
		return formatter.format(created);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}