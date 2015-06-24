/**
 * LoginAction.java
 * com.psy.action.user
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月9日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
 */
package com.psy.action.user;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.psy.entity.Login;
import com.psy.entity.Users;
import com.psy.service.user.LoginService;
import com.psy.service.user.UsersService;
import com.psy.util.Constants;
import com.psy.util.StringHelper;

@Namespace("/")
@Results({ 
	@Result(name = "login", location = "/login-qt.jsp", type = "redirect"),
	@Result(name = "index", location = "index!view.do", type = "redirect"),
	@Result(name = "success", location = "user/users!view.do", type = "redirect") 
})
public class LoginAction extends ActionSupport {

	/**
	 * serialVersionUID TODO(用一句换描述这个变量的作用)
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsersService usersService;
	@Autowired
	private LoginService loginService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = ServletActionContext.getRequest().getSession();

	@Override
	public String execute() throws Exception {
		return "login";
	}

	/**
	 * 跳转登录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		String loginName = request.getParameter("userName");
		String loginPwd = request.getParameter("pwd");
		Login login = loginService.login(loginName, loginPwd);
		Users user = null;
		if (!StringHelper.isEmptyObject(login.getUserId())) {
			user = usersService.getUsersById(login.getUserId());
		}
		if (user != null) {
			session.setAttribute(Constants.SESSION_USER_KEY, user);
		} else {
			rentJavascript("alert('用户名或密码错误!');location.href='login-qt.jsp';");
			return null;
		}
		return SUCCESS;
	}

	/**
	 * 退出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logOff() throws Exception {
		session.removeAttribute(Constants.SESSION_USER_KEY);
		return "index";
	}

	/**
	 * 修改或重置用户密码
	 */
	public void modifyUserPwd() {

	}

	/**
	 * TODO(输出json)
	 * @param jsonStr
	*/
	private void outjson(String jsonStr) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void rentJavascript(String js) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<script language=\"javascript\">");
			response.getWriter().write(js + "\n");
			response.getWriter().write("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
