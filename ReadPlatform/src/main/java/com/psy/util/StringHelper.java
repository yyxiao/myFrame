/**
 * StringHelper.java
 * com.xiao.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringHelper {

	public static String toString(Object obj) {
		if (obj == null || "".equals(obj.toString())
				|| "null".equals(obj.toString())) {
			return "";
		} else {
			String objValue = obj.toString().trim();
			return objValue;
		}
	}

	public static boolean isEmptyObject(Object obj)
	{
			if(StringHelper.toString(obj).equals("")){
				return true;
			}else{
				return false;
		} 
	}	

	public static boolean isEmptyList(List list)
	{
			if(list!=null&&list.size()>0){
				return false;
			}else{
				return true;
		} 
	}

	public static boolean isEmptyMap(Map map)
	{
			if(map!=null&&map.size()>0){
				return false;
			}else{
				return true;
		} 
	}
	
	/**
	 * TODO(str字符串中是否包含中文)
	 * @param str
	 * @return
	*/
	public static boolean isContainsChinese(String str)	{
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()){
			flg = true;
		}
		return flg;
	}
}
