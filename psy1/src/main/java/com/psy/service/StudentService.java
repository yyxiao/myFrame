/**
 * StudentService.java
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
import java.util.Map;

import com.psy.entity.Student;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
/**
 * ClassName:StudentService
 *
 * TODO(StudentService接口类)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月17日 下午4:20:09	
 *
 * @class com.psy.service.StudentService
 *
 */ 
public interface StudentService {
	/**
	 * 保存信息
	 * @param sysLog
	 */
	public void save(Student student) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param pkId
	 * @return
	 */
	public Student getStudentById(String pkId) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteStudent(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param pkId
	 * @return
	 */
	public List<Student> getStudentList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param pkId
	 * @return
	 */
	public Page<Student> searchStudent(Page<Student> page,List<PropertyFilter> filters);
}
