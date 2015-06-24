/**
 * BookService.java
 * com.psy.service.book
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月3日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.book;

import java.util.List;

import com.psy.entity.Book;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;

 
public interface BookService {
	/**
	 * 保存信息
	 * @param 
	 */
	public void save(Book book) throws ServiceException;
	/**
	 * 通过件ID获取信息
	 * @param id
	 * @return
	 */
	public Book getBookById(String id) throws ServiceException;
	
	/**
	 * 
	 * 方法描述 : 根据id删除
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteBook(String id)throws ServiceException;
	/**
	 * 获取列表信息
	 * @param id
	 * @return
	 */
	public List<Book> getBookList()throws ServiceException;
	/**
	 * 分页查询列表信息
	 * @param id
	 * @return
	 */
	public Page<Book> searchBook(Page<Book> page,List<PropertyFilter> filters);
	
	/**
	 * TODO(通过推荐属性查询Page)
	 * @param recommend
	 * @return
	 * @throws ServiceException
	*/
	public Page<Book> findBooksByRec(Page<Book> page,String recommend)throws ServiceException;
	
	/**
	 * TODO(通过推荐属性查询BookList)
	 * @param num
	 * @param recommend
	 * @return
	 * @throws ServiceException
	*/
	public List findBooksByRecAndNum(String num,String recommend)throws ServiceException;
	
	/**
	 * TODO(随机从表中抽取num条数据)
	 * @param num
	 * @return
	 * @throws ServiceException
	*/
	public List randomBooks(String num)throws ServiceException;
	
	/**
	 * TODO(通过关键字多字段查询书籍)
	 * @param keywords
	 * @return
	 * @throws ServiceException
	*/
	public Page<Book> searchBooksByKeyword(Page<Book> page,String keywords)throws ServiceException;
}
