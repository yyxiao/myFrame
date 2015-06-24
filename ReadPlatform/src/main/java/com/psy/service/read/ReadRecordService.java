/**
 * ReadRecordService.java
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

import com.psy.entity.ReadRecord;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
public interface ReadRecordService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(ReadRecord readRecord) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param id
	 * @return
	 */
	public ReadRecord getReadRecordById(String id) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteReadRecord(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<ReadRecord> getReadRecordList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<ReadRecord> searchReadRecord(Page<ReadRecord> page,List<PropertyFilter> filters);
	/**
	 * 通过用户id，图书id，type查询记录
	 * @param id
	 * @return
	 */
	public List<ReadRecord> searchReadRecordByParam(String userId,String bookId,String type, String num);
	/**
	 * TODO(通过用户id，type查询记录)
	 * @param userId
	 * @param type
	 * @param num
	 * @return
	*/
	public List<ReadRecord> searchReadRecordByUserId(String userId,String type, String num)throws ServiceException;
}
