/**
 * UsersServiceImpl.java
 * com.psy.service.user.impl
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月8日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.dao.user.UsersDao;
import com.psy.entity.Users;
import com.psy.service.user.UsersService;
import com.psy.util.JdbcDAO;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("usersServiceImpl")
@Transactional
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public void save(Users users) throws ServiceException {
		if (StringHelper.isEmptyObject(users.getUserId())) {
			usersDao.onlySave(users);
		} else {
			usersDao.merge(users);
		}
	}

	@Override
	public Users getUsersById(String pkId) throws ServiceException {
		List<Users> userss = usersDao.findBy("userId", pkId);
		if (!userss.isEmpty()) {
			return userss.get(0);
		}
		return null;
	}

	@Override
	public void deleteUsers(String id) throws ServiceException {
		Users users = getUsersById(id);
		usersDao.delete(users);
	}

	@Override
	public List<Users> getUsersList() throws ServiceException {
		List<Users> userss = usersDao.getAll();
		return userss;
	}

	@Override
	public Page<Users> searchUsers(Page<Users> page, List<PropertyFilter> filters) {
		try {
			return usersDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询用户失败:" + e.getMessage());
		}
	}

	@Override
	public List<Users> findUsersRank(String num)throws ServiceException {
		String sql = "SELECT * FROM `users` ORDER BY users.read_num DESC LIMIT 0,"+num;
		List<Users> userlist = jdbcDAO.findForList(sql);
		return userlist;
	}

}
