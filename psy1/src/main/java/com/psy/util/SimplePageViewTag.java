/**
 * SimplePageViewTag.java
 * com.psy.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年5月25日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ClassName:SimplePageViewTag
 *
 * TODO(分页标签)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年5月25日 下午1:05:56	
 *
 * @class com.psy.util.SimplePageViewTag
 *
 */ 
public class SimplePageViewTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(getClass().getName());
	public static final String PAGE_REQUEST_ATTIBUTE_NAME = "pager"; // 存储在request中分页对象的键名
	public static final String PAGE_PAGENO_KEY_NAME = "pageNo"; // url参数上的页数键名
	public static final String PAGE_PAGESIZE_KEY_NAME = "pageSize"; // url参数上的页数键名
	private String width;
	private String url; // 列表页面传递过来的将要跳转的地址。

	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Object obj = request.getAttribute(PAGE_REQUEST_ATTIBUTE_NAME);
		StringBuffer pageString = new StringBuffer();
		Page page = null;
		long totalCount = 0; // 总记录数
		long totalPagesCount = 0; // 总页数
		long currPageNo = 0; // 当前页
		long curPageSize = 0;// 当前页大小
		long firstPage = 0; // 首页
		long prevPage = 0; // 上一页
		long nextPage = 0; // 下一页
		long lastPage = 0; // 末页

		if (obj != null) {
			try {
				page = (Page) obj;
				totalCount = page.getTotalCount();
				totalPagesCount = page.getTotalPages();
				currPageNo = page.getPageNo();
				curPageSize = page.getPageSize();
				firstPage = page.getFirstPage();
				prevPage = page.getPrePage();
				nextPage = page.getNextPage();
				lastPage = page.getTotalPages();// 取最后一页
			} catch (Exception e) {
				log.error("从request中取到分页对象进行转换时出错：" + e.getMessage());
			}
		}
		// 无数据不分页
		if (page == null || totalPagesCount == 0)
			return SKIP_BODY;
		//保存分页大小对象
		request.getSession().setAttribute("s_pageSize_", (int) curPageSize);
		url = (request.getContextPath()+"/" + getUrl()).replaceAll("//", "/");
		if (url.indexOf("?") > -1)
			url = url.substring(0, url.indexOf("?")); // 获取传参前的地址
		url += "?method=get";
		Enumeration enumer = (Enumeration) request.getParameterNames();
		int i = 0;
		while (enumer.hasMoreElements()) {
			String name = enumer.nextElement().toString();
			if (!name.equals(PAGE_PAGENO_KEY_NAME) && !name.equals(PAGE_PAGESIZE_KEY_NAME) && StringUtils.isNotBlank(request.getParameter(name))) {
				url += "&" + name + "=" + request.getParameter(name);
				i++;
			}
		}
		//url += (i == 0 ? "?" : "&") + PAGE_PAGENO_KEY_NAME + "=";

		try {
			JspWriter out = pageContext.getOut();
			String path = request.getContextPath();
			pageString.append("<style> .butt{width:70px;background:#ffffff;border: 1px solid #ccc;height: 27px;cursor:pointer;}.butt:hover { width:70px;background:#ffffff;border: 1px solid #90B0C5;height: 27px; }</style>");
			pageString.append("<script type=\"text/javascript\">var page_=").append(totalPagesCount > 1).append(";var baseurl='").append(url).append("';function jup_page(n_,s_){var page_no=").append(currPageNo).append(",page_size=").append(curPageSize)
					.append(";if(n_&&n_!=page_no) page_no=n_;if(s_&&s_!=page_size) page_size=s_;location.href=baseurl+'&").append(PAGE_PAGENO_KEY_NAME).append("='+page_no+'&").append(PAGE_PAGESIZE_KEY_NAME).append("='+page_size;}</script><br/>");
			pageString.append("<div  class=\"toolbar\"><div style='float:left;'> ");
			// 首页
			pageString.append("&nbsp;<button class='butt' style=\"background-image: url('/psy/res/icons/16x16/arrow_redo.png'); background-position: 5px; center;background-repeat: no-repeat;\"");
			if (firstPage != currPageNo)
				pageString.append(" onclick=\"jup_page(").append(firstPage).append(")\"");
			else
				pageString.append(" disabled");
			pageString.append(">&nbsp;&nbsp;&nbsp;首页</button>");
			// 上一页
			pageString.append("&nbsp;<button class='butt' style=\"background-image: url('/psy/res/icons/16x16/arrow_left.png'); background-position: 5px; center;background-repeat: no-repeat;\"");
			if (page.isHasPre())
				pageString.append(" onclick=\"jup_page(").append(prevPage).append(")\"");
			else
				pageString.append(" disabled");
			pageString.append(">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上一页&nbsp;&nbsp;</button>");
			// 下一页
			pageString.append("&nbsp;<button class='butt' style=\"background-image: url('/psy/res/icons/16x16/arrow_right.png'); background-position: 5px; center;background-repeat: no-repeat;\"");
			if (page.isHasNext())
				pageString.append(" onclick=\"jup_page(").append(nextPage).append(")\"");
			else
				pageString.append(" disabled");
			pageString.append(">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下一页&nbsp;&nbsp;</button>");
			// 尾页
			pageString.append("&nbsp;<button class='butt' style=\"background-image: url('/psy/res/icons/16x16/arrow_undo.png'); background-position: 5px; center;background-repeat: no-repeat;\"");
			if (lastPage != currPageNo)
				pageString.append(" onclick=\"jup_page(").append(lastPage).append(")\"");
			else
				pageString.append(" disabled");
			pageString.append(">&nbsp;&nbsp;&nbsp;末页</button></div><div style='float:left;height: 35px;line-height:  28px;font-size:13px;'>");
			//
			pageString.append("&nbsp;共<span style='color:red;'>").append(totalCount).append("</span>条记录&nbsp;&nbsp;计<span  style='color:red;'>").append(totalPagesCount).append("</span>页&nbsp;当前");
			pageString.append("</div><div style='float:left;margin-top: 5px;'><select  id=\"sel_page_\" onchange=\"jup_page($('#sel_page_').val())\">");
			for (int k = 1; k <= totalPagesCount; k++)
				pageString.append("<option value=").append(k).append(currPageNo == k ? " selected" : "").append(">第").append(k).append("页</option>");
			pageString.append("</select></div><div style='float:left;height: 35px;line-height:  28px;font-size:13px;'>");
			pageString.append("&nbsp;每页显示</div><div style='float:left;margin-top: 5px;'><select id=\"sel_size_\" onchange=\"jup_page(1,$('#sel_size_').val())\">");
			pageString.append("<option value=10").append(curPageSize == 10 ? " selected" : "").append(">10条</option>");
			pageString.append("<option value=15").append(curPageSize == 15 ? " selected" : "").append(">15条</option>");
			pageString.append("<option value=20").append(curPageSize == 20 ? " selected" : "").append(">20条</option>");
			pageString.append("<option value=50").append(curPageSize == 50 ? " selected" : "").append(">50条</option>");
			pageString.append("<option value=100").append(curPageSize == 100 ? " selected" : "").append(">100条</option>");
			pageString.append("</select></div></div>");

			out.println(pageString.toString());
		} catch (Exception e) {
			throw new JspException(e.toString());
		}
		return SKIP_BODY;
	}

	public String getWidth() {
		if (StringUtils.isBlank(width)) {
			width = "100%";
		}
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
