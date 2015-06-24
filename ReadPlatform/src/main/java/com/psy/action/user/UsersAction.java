/**
 * UsersAction.java
 * com.psy.action.user
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月8日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.action.user;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.psy.action.CrudActionSupport;
import com.psy.entity.BooksAll;
import com.psy.entity.BooksCollection;
import com.psy.entity.ReadRecord;
import com.psy.entity.Users;
import com.psy.service.book.BooksAllService;
import com.psy.service.book.BooksCollectionService;
import com.psy.service.read.ReadRecordService;
import com.psy.service.user.UsersService;
import com.psy.util.Constants;
import com.psy.util.HibernateWebUtils;
import com.psy.util.Page;
import com.psy.util.PropertyFilter;
import com.psy.util.StringHelper;

/**
 * ClassName:UsersAction
 *
 * TODO(UserAction)
 *
 * @project ReadPlatform
 *
 * @author xiao
 *
 * @date   2015年6月8日 下午2:29:02	
 *
 * @class com.psy.action.user.UserAction
 *
 */ 
@Results({ 
	@Result(name="list",location="user-list.jsp"), 
	@Result(name="input",location="user-input.jsp"), 
	@Result(name="view",location="user-view.jsp"), 
	@Result(name="success",location="users.do",type="redirect")
})
public class UsersAction extends CrudActionSupport<Users>{
	
	private Users users;

	private static final long serialVersionUID = 1L;
	private Users user = (Users)getSession().getAttribute(Constants.SESSION_USER_KEY);
	@Autowired
	private UsersService usersService;
	@Autowired
	private BooksAllService booksAllService;
	@Autowired
	private BooksCollectionService booksCollectionService;
	@Autowired
	private ReadRecordService readRecordService;

	@Override
	public String list() throws Exception {
		pager = this.getPage();
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(getRequest());
		savePage(usersService.searchUsers(pager, filters));
		return LIST;
	}
	
	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	/**
	 * TODO(用户页面)
	 * @return
	*/
	public String view(){
		//必读书单
		Page<BooksCollection> pager1 = new Page<BooksCollection>();
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(getRequest());
		Page<BooksCollection> bcPage = booksCollectionService.searchBooksCollection(pager1, filters);
		//想读书单
		List<BooksAll> bookA = booksAllService.findBooksAllByType(user.getUserId(),Constants.RECORD_READ_WANT);
		//在读书单
		List<BooksAll> bookB = booksAllService.findBooksAllByType(user.getUserId(),Constants.RECORD_READING);
		//已读书单
		List<BooksAll> bookC = booksAllService.findBooksAllByType(user.getUserId(),Constants.RECORD_READED);
		//查询用户评论
		List<ReadRecord> readComment = readRecordService.searchReadRecordByUserId(
				user.getUserId(), Constants.RECORD_COMMENT, "5");
		//
		List<ReadRecord> hisRead = readRecordService.searchReadRecordByUserId(
				user.getUserId(), "", "15");
		
		getRequest().setAttribute("bcPage", bcPage);
		getRequest().setAttribute("user", user);
		getRequest().setAttribute("bookA", bookA);
		getRequest().setAttribute("bookAsize", bookA.size());
		getRequest().setAttribute("bookB", bookB);
		getRequest().setAttribute("bookBsize", bookB.size());
		getRequest().setAttribute("bookC", bookC);
		getRequest().setAttribute("bookCsize", bookC.size());
		getRequest().setAttribute("readComment", readComment);
		getRequest().setAttribute("hisRead", hisRead);
		
		return "view";
	}

	@Override
	public String save() throws Exception {
		System.out.println("****用户基本信息存储****");
		if(!StringHelper.isEmptyObject(users.getName())){
			usersService.save(users);
		}
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		usersService.deleteUsers(id);
		System.out.println("删除用户信息成功");
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(id)){
			users = usersService.getUsersById(id);
		}else{
			users = new Users();
		}
	}

	@Override
	public Users getModel() {
		return null;
	}
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
