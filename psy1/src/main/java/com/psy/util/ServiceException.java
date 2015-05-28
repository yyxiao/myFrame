/**
 * ServiceException.java
 * com.xiao.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

/**
 * ClassName:ServiceException
 *
 * TODO(Service层公用的Exception,继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月17日 下午2:02:35	
 *
 * @class com.xiao.util.ServiceException
 *
 */ 
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1401593546385403720L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(String exceptionType , String message, Throwable cause) {
		super(message, cause);
	}
}
