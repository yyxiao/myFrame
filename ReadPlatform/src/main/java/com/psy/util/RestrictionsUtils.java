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

public class RestrictionsUtils {
	public RestrictionsUtils(){  
	}  
	
	public static Criterion ilike(final String propertyName, String value, MatchMode matchMode) {  
	    return new IlikeExpressionEx(propertyName, value, matchMode);  
	}  
}
