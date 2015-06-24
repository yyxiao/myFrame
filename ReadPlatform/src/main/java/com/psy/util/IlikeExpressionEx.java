/**
 * IlikeExpressionEx.java
 * com.xiao.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.IlikeExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQLDialect;

public class IlikeExpressionEx extends IlikeExpression{  
    
    private final String propertyName;  
    private final Object value;  
      
    protected IlikeExpressionEx(String propertyName, Object value) {  
        super(propertyName, value);  
        this.propertyName = propertyName;  
        this.value = value.toString();  
    }  
    
    protected IlikeExpressionEx(String propertyName, String value, MatchMode matchMode) {  
        this( propertyName, matchMode.toMatchString(escapeSQLLike(value.toString())));  
    }  
    
    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {   
        Dialect dialect = criteriaQuery.getFactory().getDialect();  
        String[] columns = criteriaQuery.getColumnsUsingProjection(criteria, propertyName);  
        if (columns.length!=1) throw new HibernateException("ilike may only be used with single-column properties");  
        if ( dialect instanceof PostgreSQLDialect ) {  
            return columns[0] + " ilike ? escape '/'";  
        }  
        else {  
            return dialect.getLowercaseFunction() + '(' + columns[0] + ") like ? escape '/'";  
        }  
    }  
    
    public static String escapeSQLLike(String likeStr) {  
        String str = StringUtils.replace(likeStr, "_", "/_");  
        str = StringUtils.replace(str, "%",    "/%");  
        str = StringUtils.replace(str, "?", "_");  
        str = StringUtils.replace(str, "*", "%");  
        return str;  
    }  
}  