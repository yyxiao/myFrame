/**
 * QuestionService.java
 * com.psy.service.read
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月9日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.read;

import java.util.List;

import com.psy.entity.Question;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
public interface QuestionService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(Question question) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param id
	 * @return
	 */
	public Question getQuestionById(String id) throws ServiceException;
	
	/**
	 * 删除
	 * @throws ServiceException
	 */
	public void deleteQuestion(List<Question> questions)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<Question> getQuestionListByBookId(String bookId)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public Question getQuestionByParas(String bookId,String remark)throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<Question> searchQuestion(Page<Question> page,List<PropertyFilter> filters);
}
