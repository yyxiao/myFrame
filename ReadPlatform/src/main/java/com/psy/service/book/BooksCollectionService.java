/**
 * BooksCollectionService.java
 * com.psy.service.book
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月8日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.book;

import java.util.List;

import com.psy.entity.BooksCollection;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
/**
 * ClassName:BooksCollectionService
 *
 * TODO(必读图书service)
 *
 * @project ReadPlatform
 *
 * @author xiao
 *
 * @date   2015年6月8日 下午5:10:25	
 *
 * @class com.psy.service.book.BooksCollectionService
 *
 */ 
public interface BooksCollectionService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(BooksCollection booksCollection) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param id
	 * @return
	 */
	public BooksCollection getBooksCollectionById(String id) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteBooksCollection(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<BooksCollection> getBooksCollectionList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<BooksCollection> searchBooksCollection(Page<BooksCollection> page,List<PropertyFilter> filters);
}
