/**
 * BooksCollectionDao.java
 * com.psy.dao.book
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月8日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.dao.book;

import org.springframework.stereotype.Repository;

import com.psy.entity.BooksCollection;
import com.psy.util.HibernateDao;

@Repository("booksCollectionDao")
public class BooksCollectionDao extends HibernateDao<BooksCollection, String>{

}
