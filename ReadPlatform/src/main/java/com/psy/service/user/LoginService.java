/**
 * LoginService.java
 * com.psy.service.user
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月8日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.user;

import java.util.List;

import com.psy.entity.Login;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
public interface LoginService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(Login login) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param id
	 * @return
	 */
	public Login getLoginById(String id) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteLogin(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<Login> getLoginList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<Login> searchLogin(Page<Login> page,List<PropertyFilter> filters);
	
	/**
	 * 通过userId获取信息
	 * @param id
	 * @return
	 */
	public Login getLoginByUserId(String userId) throws ServiceException;
	
	/**
	 * 通过loginName,password获取信息
	 * @param id
	 * @return
	 */
	public Login login(String loginName,String password) throws ServiceException;
	
}
