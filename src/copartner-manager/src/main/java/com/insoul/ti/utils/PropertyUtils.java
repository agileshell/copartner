package com.insoul.ti.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月5日 下午11:01:05
 */
public class PropertyUtils {
	
	public static String toString(Collection<String> collection) {
		StringBuilder sb = new StringBuilder();
		if(collection == null || collection.isEmpty()) {
			return sb.toString();
		}
		for (String coll : collection) {
			sb.append(coll).append(Constants.DOT_SPLIT);
		}
		return sb.toString();
	}
	
	public static Set<String> toSet(String collection) {
		Set<String> set = new HashSet<String>();
		if(StringUtils.isBlank(collection)) {
			return set;
		}
		String[] collectionArray = StringUtils.split(collection, Constants.DOT_SPLIT);
		if(collectionArray == null || collectionArray.length <= 0) {
			return set;
		}
		set.addAll(Arrays.asList(collectionArray));
		return set;
	}

	public static Map<String, String> toMap(String properties) {
		Map<String, String> props = new HashMap<String, String>();
		if(StringUtils.isBlank(properties)) {
			return props;
		}
		String[] propsArray = StringUtils.split(properties, Constants.PROPERTIES_SPLIT);
		if(propsArray == null || propsArray.length <= 0) {
			return props;
		}
		for (String prop : propsArray) {
			String[] propArray = StringUtils.split(prop, Constants.EQ_SPLIT);
			if(propArray == null || propArray.length != 2) {
				continue;
			}
			props.put(propArray[0], propArray[1]);
		}
		return props;
	}
	
	public static String toString(Map<String, String> properties) {
		StringBuilder sb = new StringBuilder(StringUtils.EMPTY);
		if(properties == null || properties.isEmpty()) {
			return sb.toString();
		}
		for (Map.Entry<String, String> e : properties.entrySet()) {
			sb.append(e.getKey()).append(Constants.EQ_SPLIT).append(e.getValue()).append(Constants.PROPERTIES_SPLIT);
		}
		return sb.toString();
	}
}