/**
 * UsersService.java
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

import com.psy.entity.Users;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
public interface UsersService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(Users users) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param id
	 * @return
	 */
	public Users getUsersById(String id) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteUsers(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<Users> getUsersList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<Users> searchUsers(Page<Users> page,List<PropertyFilter> filters);
	/**
	 * TODO(阅读排行，前num个)
	 * @param num
	 * @return
	 * @throws SecurityException
	*/
	public List<Users> findUsersRank(String num)throws SecurityException;
	
}
