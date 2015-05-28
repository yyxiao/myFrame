/**
 * TeacherService.java
 * com.psy.service
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service;

import java.util.List;

import com.psy.entity.Teacher;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
/**
 * ClassName:TeacherService
 *
 * TODO(TeacherService接口类)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月17日 下午4:20:09	
 *
 * @class com.psy.service.TeacherService
 *
 */ 
public interface TeacherService {
	/**
	 * 保存信息
	 * @param sysLog
	 */
	public void save(Teacher teacher) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param pkId
	 * @return
	 */
	public Teacher getTeacherById(String pkId) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteTeacher(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param pkId
	 * @return
	 */
	public List<Teacher> getTeacherList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param pkId
	 * @return
	 */
	public Page<Teacher> searchTeacher(Page<Teacher> page,List<PropertyFilter> filters);
}
