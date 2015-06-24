/**
 * BooksAllService.java
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

import com.psy.entity.BooksAll;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
/**
 * ClassName:BooksAllService
 *
 * TODO(所有书单Service)
 *
 * @project ReadPlatform
 *
 * @author xiao
 *
 * @date   2015年6月8日 下午4:53:39	
 *
 * @class com.psy.service.book.BooksAllService
 *
 */ 
public interface BooksAllService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(BooksAll booksAll) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param id
	 * @return
	 */
	public BooksAll getBooksAllById(String id) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteBooksAll(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<BooksAll> getBooksAllList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<BooksAll> searchBooksAll(Page<BooksAll> page,List<PropertyFilter> filters);
	
	/**
	 * @param userId
	 * @param bookId
	 * @return
	 * @throws ServiceException
	*/
	public BooksAll findBooksAllByIds(String userId,String bookId)throws ServiceException;
	
	/**
	 * TODO(获取type书籍列表)
	 * @param type
	 * @return
	 * @throws ServiceException
	*/
	public List<BooksAll> findBooksAllByType(String userId,String type)throws ServiceException;
}
