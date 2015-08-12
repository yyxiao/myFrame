/**
 * AnswerService.java
 * com.psy.service.read
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年7月28日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.read;

import java.util.List;

import com.psy.entity.Answer;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
public interface AnswerService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(Answer answer) throws ServiceException;
	/**
	 * 通过answerID获取信息
	 * @param answerid
	 * @return
	 */
	public Answer getAnswerById(String answerId) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据answerId删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteAnswer(String answerId)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<Answer> getAnswerList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<Answer> searchAnswer(Page<Answer> page,List<PropertyFilter> filters);
}
