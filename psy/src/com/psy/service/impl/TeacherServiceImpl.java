/**
 * TeacherServiceImpl.java
 * com.psy.service.impl
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.dao.TeacherDao;
import com.psy.entity.Teacher;
import com.psy.service.TeacherService;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

/**
 * ClassName:TeacherServiceImpl
 *
 * TODO(TeacherService实现类)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月17日 下午4:26:45	
 *
 * @class com.psy.service.impl.TeacherServiceImpl
 *
 */ 
@Repository("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public void save(Teacher teacher) throws ServiceException {
		if (StringHelper.isEmptyObject(teacher.getTeachId())) {
			teacherDao.onlySave(teacher);
		} else {
			teacherDao.merge(teacher);
		}
	}

	@Override
	public Teacher getTeacherById(String pkId) throws ServiceException {
		List<Teacher> teachers = teacherDao.findBy("id", pkId);
		if (!teachers.isEmpty()){
			return teachers.get(0);
		}
		return null;
	}

	@Override
	public void deleteTeacher(String id) throws ServiceException {
		teacherDao.delete(id);
	}

	@Override
	public List<Teacher> getTeacherList() throws ServiceException {
		List<Teacher> teachers = teacherDao.getAll();
		return teachers;
	}

	@Override
	public Page<Teacher> searchTeacher(Page<Teacher> page,
			List<PropertyFilter> filters) {
		try {
			return teacherDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询学生信息失败:" + e.getMessage());
		}
	}

}
