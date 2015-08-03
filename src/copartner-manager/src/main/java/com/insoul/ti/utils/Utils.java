package com.insoul.ti.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年8月3日 下午12:01:28
 */
public class Utils {
	
	public String getShort(String src, int len) {
		if (StringUtils.length(src) < len + 1) {
			return src;
		}
		return StringUtils.substring(src, 0, len) + "...";
	}
}