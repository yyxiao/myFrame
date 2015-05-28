/**
 * StudentDao.java
 * com.psy.dao
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.dao;

import org.springframework.stereotype.Repository;

import com.psy.entity.Student;
import com.psy.util.HibernateDao;

@Repository("studentDao")
public class StudentDao extends HibernateDao<Student, String>{

}
