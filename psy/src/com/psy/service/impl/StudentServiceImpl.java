/**
 * StudentServiceImpl.java
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

import com.psy.dao.StudentDao;
import com.psy.entity.Student;
import com.psy.service.StudentService;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

/**
 * ClassName:StudentServiceImpl
 *
 * TODO(StudentService实现类)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月17日 下午4:26:45	
 *
 * @class com.psy.service.impl.StudentServiceImpl
 *
 */ 
@Repository("assAddressService")
@Transactional
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;

	@Override
	public void save(Student student) throws ServiceException {
		if (StringHelper.isEmptyObject(student.getId())) {
			studentDao.onlySave(student);
		} else {
			studentDao.merge(student);
		}
	}

	@Override
	public Student getStudentById(String pkId) throws ServiceException {
		List<Student> students = studentDao.findBy("id", pkId);
		if (!students.isEmpty()){
			return students.get(0);
		}
		return null;
	}

	@Override
	public void deleteStudent(String id) throws ServiceException {
		studentDao.delete(id);
	}

	@Override
	public List<Student> getStudentList() throws ServiceException {
		List<Student> students = studentDao.getAll();
		return students;
	}

	@Override
	public Page<Student> searchStudent(Page<Student> page,
			List<PropertyFilter> filters) {
		try {
			return studentDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询学生信息失败:" + e.getMessage());
		}
	}

}
