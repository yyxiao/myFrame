/**
 * LoginServiceImpl.java
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

import com.psy.dao.user.LoginDao;
import com.psy.entity.Login;
import com.psy.service.user.LoginService;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("loginServiceImpl")
@Transactional
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;

	@Override
	public void save(Login login) throws ServiceException {
		if (StringHelper.isEmptyObject(login.getUserId())) {
			loginDao.onlySave(login);
		} else {
			loginDao.merge(login);
		}
	}

	@Override
	public Login getLoginById(String pkId) throws ServiceException {
		List<Login> logins = loginDao.findBy("userId", pkId);
		if (!logins.isEmpty()) {
			return logins.get(0);
		}
		return null;
	}

	@Override
	public void deleteLogin(String id) throws ServiceException {
		Login login = getLoginById(id);
		loginDao.delete(login);
	}

	@Override
	public List<Login> getLoginList() throws ServiceException {
		List<Login> logins = loginDao.getAll();
		return logins;
	}

	@Override
	public Page<Login> searchLogin(Page<Login> page, List<PropertyFilter> filters) {
		try {
			return loginDao.findPage(page, filters);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("分页查询用户失败:" + e.getMessage());
		}
	}

	@Override
	public Login getLoginByUserId(String userId) throws ServiceException {
		return null;
	}

	@Override
	public Login login(String loginName, String password)
			throws ServiceException {
		String hql = "from Login t where t.loginName = ? and t.password = ?";
		List<Login> logins = loginDao.find(hql, loginName,password);
		Login login = new Login();
		if(!StringHelper.isEmptyList(logins)){
			login = logins.get(0);
		}
		return login;
	}

}
