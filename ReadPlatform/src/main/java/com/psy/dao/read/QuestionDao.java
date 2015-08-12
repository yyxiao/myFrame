/**
 * QuestionDao.java
 * com.psy.dao.read
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月9日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.dao.read;

import org.springframework.stereotype.Repository;

import com.psy.entity.Question;
import com.psy.util.HibernateDao;

@Repository("questionDao")
public class QuestionDao extends HibernateDao<Question, String>{

}
