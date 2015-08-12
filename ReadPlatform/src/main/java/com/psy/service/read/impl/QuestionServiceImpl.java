/**
 * QuestionServiceImpl.java
 * com.psy.service.read.impl
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年7月22日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.dao.read.QuestionDao;
import com.psy.entity.Question;
import com.psy.service.read.QuestionService;
import com.psy.util.JdbcDAO;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("questionServiceImpl")
@Transactional
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public void save(Question question) throws ServiceException {
		if (StringHelper.isEmptyObject(question.getQuestionId())) {
			questionDao.onlySave(question);
		} else {
			questionDao.merge(question);
		}
	}

	@Override
	public Question getQuestionById(String id) throws ServiceException {
		List<Question> question = questionDao.findBy("questionId", id);
		if (!question.isEmpty()) {
			return question.get(0);
		}
		return null;
	}

	@Override
	public void deleteQuestion(List<Question> questions) throws ServiceException {
		for (int i = 0; i < questions.size(); i++) {
			questionDao.delete(questions.get(i));
		}
	}

	@Override
	public List<Question> getQuestionListByBookId(String bookId) throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("from Question a where a.bookId= '").append(bookId).append("'");
		hql.append(" order by a.createTime desc ");
		List<Question> questions = questionDao.find(hql.toString());
		return questions;
	}

	@Override
	public Page<Question> searchQuestion(Page<Question> page,
			List<PropertyFilter> filters) {
		return null;
	}

	@Override
	public Question getQuestionByParas(String bookId, String remark) throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("from Question a where a.bookId= '").append(bookId).append("'");
		if(!StringHelper.isEmptyObject(remark)){
			hql.append(" and a.remark= '").append(remark).append("'");
		}
		hql.append(" order by a.createTime desc ");
		List<Question> questions = questionDao.find(hql.toString());
		if (!questions.isEmpty()) {
			return questions.get(0);
		}
		return null;
	}

}
