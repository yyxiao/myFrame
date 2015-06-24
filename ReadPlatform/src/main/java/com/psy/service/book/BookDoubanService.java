/**
 * BookDoubanService.java
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

import com.psy.entity.BookDouban;
import com.psy.util.ServiceException;

 
public interface BookDoubanService {
	
	/**
	 * TODO(通过Isbn从豆瓣网获取图书数据)
	 * @param isbn
	 * @return
	 * @throws Exception
	*/
	public BookDouban getBookInfoByIsbn(String isbn) throws Exception;
	
	/**
	 * TODO(从视图中获取排行数据)
	 * @param type（评分、阅读）
	 * @return
	 * @throws Exception
	*/
	public List findRandByType(String num,String type)throws ServiceException;
	
	/**
	 * TODO(通过书籍id查分)
	 * @param bookId
	 * @return
	 * @throws ServiceException
	*/
	public Object findScoreByBookId(String bookId)throws ServiceException;
	
}
