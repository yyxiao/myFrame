/**
 * ServletAction.java
 * com.psy.action
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年7月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.psy.entity.Book;
import com.psy.entity.BookDouban;
import com.psy.entity.Question;
import com.psy.service.book.BookDoubanService;
import com.psy.service.book.BookService;
import com.psy.service.read.QuestionService;
import com.psy.util.StringHelper;
import com.psy.util.TimeHelper;

@SuppressWarnings("serial")
public class ServletAction extends ActionSupport{

	/** 
	 *  serialVersionUID  
	 *  TODO(用一句换描述这个变量的作用)
	*/
	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	@Autowired
	private BookService bookService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private BookDoubanService bookDoubanService;
	
	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return StringHelper.toString(getRequest().getParameter(name)).trim();
	}
	
	/**
	 * TODO(通过Name模糊查询图书)
	*/
	public void searchByKey(){
		String name = getParameter("bookName");
		int pageSize = Integer.parseInt(getParameter("pageSize"));
		int pageNo = Integer.parseInt(getParameter("pageNo"));
		List bookList = bookService.searchByName(name, pageNo, pageSize);
		JSON json = JSONSerializer.toJSON(bookList);
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchByBookId() throws Exception {
		String bookId = getParameter("bookId");
		System.out.println("bookId：" + bookId);
		Book book = bookService.getBookById(bookId);
		JSON json = JSONSerializer.toJSON(book);
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO(保存书籍信息)
	 * @return
	 * @throws IOException 
	 * @throws Exception
	*/
	public void saveInfo() throws IOException{
		logger.info("------------ book begin saveInfo ------------");
		String bookName = getParameter("bookName");
		String subTitle = getParameter("subTitle");
		String author = getParameter("author");
		String translator = getParameter("translator");
		String drawor = getParameter("drawor");
		String editor = getParameter("editor");
		String inputer = getParameter("inputer");
		String isbn10 = getParameter("isbn10");
		String isbn13 = getParameter("isbn13");
		String nums = getParameter("nums");
		String pages = getParameter("pages");
		String authorIntro = getParameter("authorIntro");
		String publisher = getParameter("publisher");
		String pubdate = getParameter("pubdate");
		String summary = getParameter("summary");
		String price = getParameter("price");
		String smallImages = getParameter("smallImages");
		String largeImages = getParameter("largeImages");
		String mediumImages = getParameter("mediumImages");
		String images = getParameter("images");
		String dbTags = getParameter("dbTags");
		String schoolTags = getParameter("schoolTags");
//		String content = getParameter("content");
		String type = getParameter("type");
		String recommend = getParameter("recommend");
		String subject = getParameter("subject");
		String degree = getParameter("degree");
		String grade = getParameter("grade");
		String space = getParameter("space");
		String ages = getParameter("ages");
		String[]  ages1 = ages.split("-");
		String startAge = ages1[0];
		String endAge = ages1[1];
		String country = getParameter("country");
		
		Book books = bookService.getBookByIsbn13(isbn13);
		if(StringHelper.isEmptyObject(books)){
			books = new Book();
		}
		books.setAuthor(author);
		books.setTranslator(translator);
		books.setDrawor(drawor);
		books.setEditor(editor);
		books.setInputer(inputer);
		books.setAuthorIntro(authorIntro);
		books.setBookName(bookName);
		books.setSubTitle(subTitle);
		books.setCreateTime(TimeHelper.getCurrentTime());
		books.setIsbn10(isbn10);
		books.setIsbn13(isbn13);
		books.setNums(nums);
		books.setPages(pages);
		books.setImages(images);
		books.setSmallImages(smallImages);
		books.setLargeImages(largeImages);
		books.setMediumImages(mediumImages);
		books.setPrice(price);
		books.setPubdate(pubdate);
		books.setPublisher(publisher);
		books.setSummary(summary);
		books.setDbTags(dbTags);
		books.setSchoolTags(schoolTags);
		books.setRecommend(recommend);
		books.setType(type);
		books.setSubject(subject);
		books.setDegree(degree);
		books.setGrade(grade);
		books.setStartAge(startAge);
		books.setEndAge(endAge);
		books.setCountry(country);
		books.setSpace(space);
		bookService.save(books);
		//保存题目信息
		String bookId = bookService.getBookByIsbn13(isbn13).getBookId();
		if(!bookId.isEmpty()){
			String qus = getParameter("qusAll");
			String[] qusAll = qus.split("\r\n");
			System.out.println(qusAll.length);
			for (int i = 0; i < qusAll.length/3; i++) {
				String tigan = qusAll[i*3];
				String option = qusAll[i*3+1];
				String correct = qusAll[i*3+2];
				String remark = String.valueOf(i+1);
				Question question = questionService.getQuestionByParas(bookId, remark);
				if(StringHelper.isEmptyObject(question)){
					question = new Question();
				}
				question.setContent(tigan);
				question.setOptionConfig(option);
				question.setAnsVal(correct);
				question.setBookId(bookId);
				question.setRemark(remark);
				//题目是否可用
				question.setStatus("1");
				question.setCreateTime(TimeHelper.getCurrentTime());
				if(!StringHelper.isEmptyObject(tigan)){
					questionService.save(question);
				}
			}
		}
		
		//保存成功
		getResponse().getWriter().write("{\"msg\":\"0\"}");
		System.out.println("success");
		logger.info("------------ book end saveInfo------------");
	}
	
	/**
	 * TODO(是否重复)
	 * @throws IOException
	*/
	public void isRepeat() throws IOException{
		String isbn13 = getParameter("isbn13");
		Book books = bookService.getBookByIsbn13(isbn13);
		if(StringHelper.isEmptyObject(books)){
			//不重复
			getResponse().getWriter().write("{\"msg\":\"0\"}");
		}else{
			//重复
			getResponse().getWriter().write("{\"msg\":\"1\"}");
		}
	}
	
	public void saveSubTitle() throws Exception{
		List<Book> bookList = bookService.getBookList();
		for(Book book:bookList){
			String isbn13 = book.getIsbn13();
			String subTitle = book.getSubTitle();
			if(StringHelper.isEmptyObject(subTitle)){
				System.out.println(subTitle);
				// 通过isbn获取书籍详细信息
				BookDouban bookDouban = bookDoubanService.getBookInfoByIsbn(isbn13);
				book.setSubTitle(bookDouban.getSubTitle());
				System.out.println(isbn13);
				bookService.save(book);
			}
		}
	}

	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
