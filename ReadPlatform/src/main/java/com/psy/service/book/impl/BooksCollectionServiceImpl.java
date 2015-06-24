/**
 * BooksCollectionServiceImpl.java
 * com.psy.service.booksCollection.impl
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

import com.psy.dao.book.BooksCollectionDao;
import com.psy.entity.BooksCollection;
import com.psy.service.book.BooksCollectionService;
import com.psy.util.JdbcDAO;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("booksCollectionService")
@Transactional
public class BooksCollectionServiceImpl implements BooksCollectionService{

	@Autowired
	private BooksCollectionDao booksCollectionDao;
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public void save(BooksCollection booksCollection) throws ServiceException {
		if (StringHelper.isEmptyObject(booksCollection.getBookId())) {
			booksCollectionDao.onlySave(booksCollection);
		} else {
			booksCollectionDao.merge(booksCollection);
		}
	}

	@Override
	public BooksCollection getBooksCollectionById(String pkId) throws ServiceException {
		List<BooksCollection> books = booksCollectionDao.findBy("bookId", pkId);
		if (!books.isEmpty()) {
			return books.get(0);
		}
		return null;
	}

	@Override
	public void deleteBooksCollection(String id) throws ServiceException {
		BooksCollection booksCollection = getBooksCollectionById(id);
		booksCollectionDao.delete(booksCollection);
	}

	@Override
	public List<BooksCollection> getBooksCollectionList() throws ServiceException {
		List<BooksCollection> books = booksCollectionDao.getAll();
		return books;
	}

	@Override
	public Page<BooksCollection> searchBooksCollection(Page<BooksCollection> page, List<PropertyFilter> filters) {
		try {
			return booksCollectionDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询书籍失败:" + e.getMessage());
		}
	}

}
