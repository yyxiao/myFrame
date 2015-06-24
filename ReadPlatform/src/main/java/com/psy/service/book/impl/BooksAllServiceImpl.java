/**
 * BooksAllServiceImpl.java
 * com.psy.service.booksAll.impl
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月8日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.book.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.dao.book.BooksAllDao;
import com.psy.entity.BooksAll;
import com.psy.service.book.BooksAllService;
import com.psy.util.JdbcDAO;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("booksAllService")
@Transactional
public class BooksAllServiceImpl implements BooksAllService{

	@Autowired
	private BooksAllDao booksAllDao;
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public void save(BooksAll booksAll) throws ServiceException {
		if (StringHelper.isEmptyObject(booksAll.getBookId())) {
			booksAllDao.onlySave(booksAll);
		} else {
			booksAllDao.merge(booksAll);
		}
	}

	@Override
	public BooksAll getBooksAllById(String pkId) throws ServiceException {
		List<BooksAll> books = booksAllDao.findBy("bookId", pkId);
		if (!books.isEmpty()) {
			return books.get(0);
		}
		return null;
	}

	@Override
	public void deleteBooksAll(String id) throws ServiceException {
		BooksAll booksAll = getBooksAllById(id);
		booksAllDao.delete(booksAll);
	}

	@Override
	public List<BooksAll> getBooksAllList() throws ServiceException {
		List<BooksAll> books = booksAllDao.getAll();
		return books;
	}

	@Override
	public Page<BooksAll> searchBooksAll(Page<BooksAll> page, List<PropertyFilter> filters) {
		try {
			return booksAllDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询书籍失败:" + e.getMessage());
		}
	}

	@Override
	public BooksAll findBooksAllByIds(String userId, String bookId)
			throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("from BooksAll t where t.userId = ? and t.bookId = ?");
		List<BooksAll> books = booksAllDao.find(hql.toString(), userId,bookId);
		if(!StringHelper.isEmptyList(books)){
			BooksAll booksAll = books.get(0);
			return booksAll;
		}
		return null;
	}

	@Override
	public List<BooksAll> findBooksAllByType(String userId,String type)
			throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("from BooksAll t where t.userId = ? and t.type = ? order by t.createTime desc");
		List<BooksAll> books = booksAllDao.find(hql.toString(), userId,type);
		return books;
	}

}
