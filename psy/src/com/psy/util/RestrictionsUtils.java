/**
 * RestrictionsUtils.java
 * com.xiao.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;

/**
 * 解决特殊字符查询的问题
 * @author ht
 * @date 2011.8.17
 */
public class RestrictionsUtils {
	public RestrictionsUtils(){  
	}  
	
	/** 
	 *  
	 * @description:处理字符串中含转义字符问题 
	 * @return 
	 */  
	public static Criterion ilike(final String propertyName, String value, MatchMode matchMode) {  
	    return new IlikeExpressionEx(propertyName, value, matchMode);  
	}  
}
