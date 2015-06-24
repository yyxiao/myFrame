/**
 * PropertyFilter.java
 * com.xiao.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
public class PropertyFilter {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	public static final String OR_SEPARATOR = "_";

	public enum MatchType {
		EQ, LIKE, LT, GT, LE, GE, IN;
	}

	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class);

		private Class<?> clazz;

		PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	private String[] propertyNames = null;
	private Class<?> propertyType = null;
	private Object propertyValue = null;
	private MatchType matchType = null;

	public PropertyFilter() {
	}

	public PropertyFilter(final String filterName, final String value) {

		String matchTypeStr = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(matchTypeStr, 0, matchTypeStr.length() - 1);
		String propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
		try {
			matchType = Enum.valueOf(MatchType.class, matchTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}

		try {
			propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		propertyNames = StringUtils.split(propertyNameStr, PropertyFilter.OR_SEPARATOR);
		if(propertyNames.length > 1) {
			logger.info("filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		}
		
		//按entity property中的类型将字符串转化为实际类型.
		this.propertyValue = ReflectionUtils.convertStringToObject(value, propertyType);
	}

	public boolean isMultiProperty() {
		return (propertyNames.length > 1);
	}

	public String[] getPropertyNames() {
		return propertyNames;
	}

	public String getPropertyName() {
		if (propertyNames.length > 1) {
			throw new IllegalArgumentException("There are not only one property");
		}
		return propertyNames[0];
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public Class<?> getPropertyType() {
		return propertyType;
	}

	public MatchType getMatchType() {
		return matchType;
	}
	public static void main(String args[]){
		String ss="rssOp";
		String s[]=StringUtils.split(ss, "_OR_");
	}
}
