/**
 * IndexAction.java
 * com.psy.action
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月4日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.psy.entity.Book;
import com.psy.entity.Users;
import com.psy.service.book.BookDoubanService;
import com.psy.service.book.BookService;
import com.psy.service.user.UsersService;
import com.psy.util.Constants;
import com.psy.util.Page;

 
/**
 * ClassName:IndexAction
 *
 * TODO(首页Action)
 *
 * @project ReadPlatform
 *
 * @author xiao
 * @param <T>
 *
 * @date   2015年6月4日 上午9:24:10	
 *
 * @class com.psy.action.IndexAction
 *
 */ 
@Results({ 
	@Result(name="index",location="index.jsp"), 
	@Result(name="success",location="index!view.do",type="redirect")
})
public class IndexAction extends CrudActionSupport<Book>{
	
	private static final long serialVersionUID = 1L;
	
	private Users user = (Users)getSession().getAttribute(Constants.SESSION_USER_KEY);
	
	@Autowired
	private BookService bookService;
	@Autowired
	private BookDoubanService bookDoubanService;
	@Autowired
	private UsersService usersService;

	/**
	 * TODO(index view)
	 * @return
	*/
	public void randomBook() {
		//猜你喜欢
		List randList = bookService.randomBooks("5");
		getRequest().setAttribute("randList", randList);
	}
	
	/**
	 * TODO(index view)
	 * @return
	*/
	public String view() {
		pager = this.getPage();
		System.out.println("*****************首页显示********************");
		Page<Book> books = bookService.findBooksByRec(pager,"00A");
		//学校推荐
		List bookList = bookService.findBooksByRecAndNum("5", Constants.RECOMMEND_SCHOOL);
		//出版社推荐
		List publishList = bookService.findBooksByRecAndNum("5", Constants.RECOMMEND_PUBLISH);
		//阅读排行
		List readList = bookDoubanService.findRandByType("5", Constants.RANK_READ);
		//评分排行
		List scoreList = bookDoubanService.findRandByType("5", Constants.RANK_SCORE);
		//阅读达人
		List userList = usersService.findUsersRank("5");
		
		//猜你喜欢
		List randList = bookService.randomBooks("5");
		getRequest().setAttribute("books", books);
		getRequest().setAttribute("randList", randList);
		getRequest().setAttribute("readList", readList);
		getRequest().setAttribute("scoreList", scoreList);
		getRequest().setAttribute("booklist", bookList);
		getRequest().setAttribute("publishList", publishList);
		getRequest().setAttribute("userList", userList);
		getRequest().setAttribute("user", user);
		return "index";
	}

	@Override
	public Book getModel() {
		return null;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
	}
}
