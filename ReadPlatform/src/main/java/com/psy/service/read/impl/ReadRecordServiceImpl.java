/**
 * ReadRecordServiceImpl.java
 * com.psy.service.read
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月9日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.dao.read.ReadRecordDao;
import com.psy.entity.ReadRecord;
import com.psy.service.read.ReadRecordService;
import com.psy.util.Constants;
import com.psy.util.JdbcDAO;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("readRecordServiceImpl")
@Transactional
public class ReadRecordServiceImpl implements ReadRecordService{

	@Autowired
	private ReadRecordDao readRecordDao;
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public void save(ReadRecord readRecord) throws ServiceException {
		if (StringHelper.isEmptyObject(readRecord.getBookId())) {
			readRecordDao.onlySave(readRecord);
		} else {
			readRecordDao.merge(readRecord);
		}
	}

	@Override
	public ReadRecord getReadRecordById(String pkId) throws ServiceException {
		List<ReadRecord> books = readRecordDao.findBy("id", pkId);
		if (!books.isEmpty()) {
			return books.get(0);
		}
		return null;
	}

	@Override
	public void deleteReadRecord(String id) throws ServiceException {
		ReadRecord readRecord = getReadRecordById(id);
		readRecordDao.delete(readRecord);
	}

	@Override
	public List<ReadRecord> getReadRecordList() throws ServiceException {
		List<ReadRecord> books = readRecordDao.getAll();
		return books;
	}

	@Override
	public Page<ReadRecord> searchReadRecord(Page<ReadRecord> page, List<PropertyFilter> filters) {
		try {
			return readRecordDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询记录失败:" + e.getMessage());
		}
	}

	@Override
	public List<ReadRecord> searchReadRecordByParam(String userId,
			String bookId, String type, String num) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ReadRecord a where a.bookId=? ");
		if(!StringHelper.isEmptyObject(userId)){
			hql.append(" and a.userId= '").append(userId).append("'");
		}
		if(type.equals(Constants.RECORD_SCORE)||type.equals(Constants.RECORD_COMMENT)){
			hql.append(" and a.type= '").append(type).append("'");
		}else{
			hql.append(" and a.type IN ('00A', '00B', '00C')");
		}
		hql.append(" order by a.createTime desc ");
		int num1 = Integer.valueOf(num).intValue();
		List<ReadRecord> records = readRecordDao.findLimit(hql.toString(),num1,bookId);
		return records;
	}

	@Override
	public List<ReadRecord> searchReadRecordByUserId(String userId,
			String type, String num) throws ServiceException {
		StringBuffer hql = new StringBuffer();
		hql.append("from ReadRecord a where a.userId=? ");
		if(!StringHelper.isEmptyObject(type)){
			hql.append("and a.type = ? ");
		}else{
			hql.append("and a.type IN ('00A', '00B', '00C') ");
		}
		hql.append(" order by a.createTime desc ");
		int num1 = Integer.valueOf(num).intValue();
		List<ReadRecord> records = new ArrayList<ReadRecord>();
		if(!StringHelper.isEmptyObject(type)){
			records = readRecordDao.findLimit(hql.toString(),num1,userId,type);
		}else{
			records = readRecordDao.findLimit(hql.toString(),num1,userId);
		}
		return records;
	}

}
