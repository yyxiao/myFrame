/**
 * AnswerServiceImpl.java
 * com.psy.service.read.impl
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年7月28日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.dao.read.AnswerDao;
import com.psy.entity.Answer;
import com.psy.service.read.AnswerService;
import com.psy.util.JdbcDAO;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("answerServiceImpl")
@Transactional
public class AnswerServiceImpl implements AnswerService{
	
	@Autowired
	private AnswerDao answerDao;
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public void save(Answer answer) throws ServiceException {
		if(StringHelper.isEmptyObject(answer.getAnswerId())){
			answerDao.onlySave(answer);
		}else{
			answerDao.merge(answer);
		}
	}

	@Override
	public Answer getAnswerById(String answerId) throws ServiceException {
		List<Answer> answers = answerDao.findBy("answerId", answerId);
		if (!answers.isEmpty()) {
			return answers.get(0);
		}
		return null;
	}

	@Override
	public void deleteAnswer(String answerId) throws ServiceException {
		Answer answer = getAnswerById(answerId);
		answerDao.delete(answer);
	}

	@Override
	public List<Answer> getAnswerList() throws ServiceException {
		List<Answer> answers = answerDao.getAll();
		return answers;
	}

	@Override
	public Page<Answer> searchAnswer(Page<Answer> page,
			List<PropertyFilter> filters) {
		try {
			return answerDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询记录失败:" + e.getMessage());
		}
	}

}
