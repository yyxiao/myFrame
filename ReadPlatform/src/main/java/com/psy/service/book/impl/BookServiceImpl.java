/**
 * BookServiceImpl.java
 * com.psy.service.book.impl
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月3日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
 */
package com.psy.service.book.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.dao.book.BookDao;
import com.psy.entity.Book;
import com.psy.service.book.BookService;
import com.psy.util.JdbcDAO;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public void save(Book book) throws ServiceException {
		if (StringHelper.isEmptyObject(book.getBookId())) {
			bookDao.onlySave(book);
		} else {
			bookDao.merge(book);
		}
	}

	@Override
	public Book getBookById(String pkId) throws ServiceException {
		List<Book> books = bookDao.findBy("bookId", pkId);
		if (!StringHelper.isEmptyList(books)) {
			return books.get(0);
		}
		return null;
	}
	
	@Override
	public Book getBookByIsbn13(String isbn13) throws ServiceException {
		List<Book> books = bookDao.findBy("isbn13", isbn13);
		System.out.println(books.size());
		if (!StringHelper.isEmptyList(books)) {
			return books.get(0);
		}
		return null;
	}
	
	@Override
	public void deleteBook(String id) throws ServiceException {
		Book book = getBookById(id);
		bookDao.delete(book);
	}

	@Override
	public List<Book> getBookList() throws ServiceException {
		List<Book> books = bookDao.getAll();
		return books;
	}

	@Override
	public Page<Book> searchBook(Page<Book> page, List<PropertyFilter> filters) {
		try {
			return bookDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询书籍失败:" + e.getMessage());
		}
	}

	@Override
	public Page<Book> findBooksByRec(Page<Book> page, String recommend)
			throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("from Book t where t.recommend like '%").append(recommend)
				.append("%' ");
		hql.append(" order by t.createTime desc");
		// String sql =
		// "SELECT * FROM `book` where recommend LIKE \"%"+recommend+"%\" ORDER BY create_time DESC";
		Page<Book> books = bookDao.findPage(page, hql.toString());
		return books;
	}

	@Override
	public List findBooksByRecAndNum(String num, String recommend)
			throws ServiceException {
		String sql = "SELECT * FROM `book` where recommend LIKE \"%"
				+ recommend + "%\" ORDER BY create_time DESC limit 0,";
		sql += num;
		List books = jdbcDAO.findForList(sql);
		return books;
	}

	@Override
	public List randomBooks(String num) throws ServiceException {
		String sql = "SELECT * FROM book  WHERE book_id >= ((SELECT MAX(book_id) FROM book )-"
				+ "(SELECT MIN(book_id) FROM book )) * RAND() + (SELECT MIN(book_id) FROM book )  LIMIT ";
		sql += num;
		List books = jdbcDAO.findForList(sql);
		return books;
	}

	@Override
	public Page<Book> searchBooksByKeyword(Page<Book> page,String keywords)
			throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("from Book t where t.author like '%").append(keywords)
				.append("%' or t.bookName like '%").append(keywords)
				.append("%' or t.publisher like '%").append(keywords)
				.append("%'");
		hql.append(" order by t.createTime desc");
		Page<Book> books = bookDao.findPage(page,hql.toString());
		return books;
	}

	@Override
	public List searchByName(String name, int pageNo, int pageSize)
			throws ServiceException {
		int startNo = pageNo>1?((pageNo-1)*pageSize-1):0;
		System.out.println(startNo);
		String sql = "SELECT * FROM `book` WHERE book_name LIKE \"%"
				+ name + "%\" LIMIT "+startNo+","+pageSize;
		List bookList = jdbcDAO.findForList(sql);
		return bookList;
	}

}
