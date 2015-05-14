/**
 * StudentServiceTest.java
 * com.psy.service
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月23日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.psy.AbstractTestCase;
import com.psy.entity.Student;

 
/**
 * ClassName:StudentServiceTest
 *
 * TODO(StudentService测试类)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月23日 上午10:05:30	
 *
 * @class com.psy.service.StudentServiceTest
 *
 */ 
public class StudentServiceTest extends AbstractTestCase{
	
	@Autowired
	private StudentService studentService;

	@Test
	//Rollback事务是否回滚，如果为true，则事务将被回滚，否则事务将被提交
	@Rollback(false)
	public void testSave() {
		Student student = new Student();
		student.setUser("测试JunitDemo");
		student.setPsw("12434354d");
		studentService.save(student);
		student = studentService.getStudentById(student.getId());
		assertEquals("测试JunitDemo",student.getUser());
		assertTrue(true);
	}

	@Test
	public void testGetStudentById() {
		String pkId = "1";
		Student stu = studentService.getStudentById(pkId);
		assertNotNull(stu);
	}

	@Test
	public void testDeleteStudent() {
		Student student = new Student();
		student.setUser("测试JunitDemo");
		student.setPsw("12434354d");
		studentService.save(student);
		Student stu1 = studentService.getStudentById(student.getId());
		assertNotNull(stu1);
		studentService.deleteStudent(student.getId());
		Student stu2 = studentService.getStudentById(student.getId());
		assertNull(stu2);
	}

}
