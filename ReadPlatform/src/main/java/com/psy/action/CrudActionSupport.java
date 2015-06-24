/**
 * CrudActionSupport.java
 * com.psy.action
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年5月25日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.psy.util.Page;
import com.psy.util.SimplePageViewTag;
import com.psy.util.StringHelper;

@SuppressWarnings("serial")
public abstract class CrudActionSupport<T> extends ActionSupport implements ModelDriven<T>, Preparable {

	public static final String RELOAD = "reload";
	public static final String LIST = "list";
	private List<String> fieldList = new ArrayList<String>();
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private String method;//默认get方式
	protected Page<T> pager;
	private Integer pageNo;// 当前页
	private Integer pageSize;// 分页大小
	private String orderBy;// 排序字段
	private String order;// 排序方向
	protected String id;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return StringHelper.toString(getRequest().getParameter(name)).trim();
	}

	/**
	 * 获取分页page对象
	 * @return
	 */
	public Page<T> getPage() {
		if (pager != null)
			return pager;
		pager = new Page<T>();
//		Integer session_pageSize = (Integer) getSession().getAttribute("");
		Integer session_pageSize = null;
		if (pageNo != null)
			pager.setPageNo(pageNo);
		if (pageSize != null)
			pager.setPageSize(pageSize);
		else if (session_pageSize != null)
			pager.setPageSize(session_pageSize);
		if (orderBy != null)
			pager.setOrderBy(orderBy);
		if (order != null)
			pager.setOrder(order);
		return pager;
	}

	// -- action信息提示 --//
	protected void saveMessage(String message) {
		getSession().setAttribute("messages", message);
	}
	// -- action信息提示 --//
	protected void saveMessage1(String message) {
		getSession().setAttribute("messages1", message);
	}
	/**
	 * 临时中转page
	 * @param pager
	 */
	public void savePage(Page<T> pager) {
		getRequest().setAttribute("messages", getSession().getAttribute("messages"));
		getSession().removeAttribute("messages");
		getRequest().setAttribute(SimplePageViewTag.PAGE_REQUEST_ATTIBUTE_NAME, pager);
	}

	@Override
	public String execute() throws Exception {
		return list();
	}

	public abstract String list() throws Exception;

	@Override
	public abstract String input() throws Exception;

	public abstract String save() throws Exception;

	public abstract String delete() throws Exception;

	public void prepare() throws Exception {
	}

	public void prepareInput() throws Exception {
		prepareModel();
	}

	public void prepareSave() throws Exception {
		prepareModel();
	}

	public void prepareView() throws Exception {
		prepareModel();
	}

	protected abstract void prepareModel() throws Exception;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	protected String getIpAddr() {
		String ipAddress = null;
		ipAddress = getRequest().getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = getRequest().getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = getRequest().getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
					if(inet!=null){
						ipAddress = inet.getHostAddress();
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}

		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()														// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
