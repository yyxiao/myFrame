/**
 * BookAction.java
 * com.psy.action.book
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月3日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
 */
package com.psy.action.book;

import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.psy.action.CrudActionSupport;
import com.psy.entity.Book;
import com.psy.entity.BookDouban;
import com.psy.entity.BooksAll;
import com.psy.entity.ReadRecord;
import com.psy.entity.Users;
import com.psy.service.book.BookDoubanService;
import com.psy.service.book.BookService;
import com.psy.service.book.BooksAllService;
import com.psy.service.read.ReadRecordService;
import com.psy.service.user.UsersService;
import com.psy.util.Constants;
import com.psy.util.HibernateWebUtils;
import com.psy.util.PropertyFilter;
import com.psy.util.StringHelper;
import com.psy.util.TimeHelper;

/**
 * ClassName:BookAction
 *
 * TODO(bookAction)
 *
 * @project ReadPlatform
 *
 * @author xiao
 *
 * @date 2015年6月3日 上午11:23:49
 *
 * @class com.psy.action.book.BookAction
 *
 */
@Results({ @Result(name = "list", location = "book-list.jsp"),
		@Result(name = "input", location = "book-input.jsp"),
		@Result(name = "view", location = "book-view.jsp"),
		@Result(name = "search", location = "book-search-list.jsp"),
		@Result(name = "view1", location = "book!view.do?bookId=${book.bookId}", type = "redirect"),
		@Result(name = "success", location = "book.do", type = "redirect") })
public class BookAction extends CrudActionSupport<Book> {

	private static final long serialVersionUID = 1L;

	private Users user = (Users) getSession().getAttribute(
			Constants.SESSION_USER_KEY);

	private Book book;
	@Autowired
	private BookService bookService;
	@Autowired
	private BookDoubanService bookDoubanService;
	@Autowired
	private BooksAllService booksAllService;
	@Autowired
	private ReadRecordService readRecordService;
	@Autowired
	private UsersService usersService;

	@Override
	public String list() throws Exception {
		pager = this.getPage();
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(getRequest());
		savePage(bookService.searchBook(pager, filters));
		return LIST;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		try {
			logger.info("------------ book begin save ------------");
			String isbn = getParameter("isbn");
			// 通过isbn获取书籍详细信息
			BookDouban bookDouban = bookDoubanService.getBookInfoByIsbn(isbn);
			book.setAuthor(bookDouban.getAuthor());
			book.setAuthorIntro(bookDouban.getAuthorIntro());
			book.setBookName(bookDouban.getBookName());
			book.setCreateTime(TimeHelper.getCurrentTime());
			book.setIsbn10(bookDouban.getIsbn10());
			book.setIsbn13(bookDouban.getIsbn13());
			book.setPages(bookDouban.getPages());
			book.setImages(bookDouban.getImage());
			book.setSmallImages(bookDouban.getSmallImage());
			book.setLargeImages(bookDouban.getLargeImage());
			book.setMediumImages(bookDouban.getMediumImage());
			book.setPrice(bookDouban.getPrice());
			book.setPubdate(bookDouban.getPubdate());
			book.setPublisher(bookDouban.getPublisher());
			book.setSummary(bookDouban.getSummary());
			book.setCommonTags(bookDouban.getCommonTags());
			book.setTranslator(bookDouban.getTranslator());
			book.setRecommend("");
			book.setType("");
			book.setSubject("");
			book.setGrade("");
			bookService.save(book);
			logger.info("------------ book begin end ------------");
		} catch (Exception e) {
			logger.error("---------book save error-----------");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String view() throws Exception {
		String bookId = getParameter("bookId");
		System.out.println("测试bookId：" + bookId);
		book = bookService.getBookById(bookId);
		// 猜你喜欢
		List randList = bookService.randomBooks("5");
		// 谁读这本书
		List<ReadRecord> readList = readRecordService.searchReadRecordByParam(
				"", bookId, "", "5");
		Object rand = bookDoubanService.findScoreByBookId(bookId);
		//查询书籍评论
		List<ReadRecord> readComment = readRecordService.searchReadRecordByParam(
				"", bookId, Constants.RECORD_COMMENT, "5");
		
		getRequest().setAttribute("rand", rand);
		getRequest().setAttribute("randList", randList);
		getRequest().setAttribute("readList", readList);
		getRequest().setAttribute("user", user);
		getRequest().setAttribute("readComment", readComment);
		getRequest().setAttribute("readCommentSize", readComment.size());
		// getRequest().setAttribute("book", book);
		return "view";
	}

	/**
	 * TODO(图书页面记录书籍状态:评分、想读、在读、读完)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String record() throws Exception {
		Users user1 = new Users();
		ReadRecord readRecord = new ReadRecord();
		String bookId = getParameter("bookId");
		// 读书状态
		String type = getParameter("type");
		String userId = getParameter("userId");
		if (!StringHelper.isEmptyObject(userId)) {
			user1 = usersService.getUsersById(userId);
		}
		System.out.println("测试bookId：" + bookId);
		book = bookService.getBookById(bookId);
		List<ReadRecord> records = readRecordService.searchReadRecordByParam(
				userId, bookId, type, "5");
		if(!StringHelper.isEmptyList(records)&&type.equals(Constants.RECORD_SCORE)){
			ReadRecord readRecord1 = records.get(0);
			String score = getParameter("score");
			readRecord1.setScore(score);
			readRecordService.save(readRecord1);
		}else {
			if (type.equals(Constants.RECORD_READ_WANT)
					|| type.equals(Constants.RECORD_READING)
					|| type.equals(Constants.RECORD_READED)) {
				BooksAll booksAll = booksAllService.findBooksAllByIds(userId, bookId);
				if(StringHelper.isEmptyObject(booksAll)){
					booksAll = new BooksAll();
					booksAll.setBookId(bookId);
					booksAll.setUserId(userId);
					booksAll.setBookName(book.getBookName());
					booksAll.setImages(book.getImages());
					booksAll.setAuthor(book.getAuthor());
					booksAll.setLargeImages(book.getLargeImages());
					booksAll.setMediumImages(book.getMediumImages());
					booksAll.setPublisher(book.getPublisher());
					booksAll.setSmallImages(book.getSmallImages());
				}
				booksAll.setType(type);
				booksAll.setCreateTime(TimeHelper.getCurrentTime());
				if(type.equals(Constants.RECORD_READED)){
					//计算用户读书总量
					int num = user.getReadNum();
					user.setReadNum(num+1);
					usersService.save(user);
				}
				booksAllService.save(booksAll);
			} else if (type.equals(Constants.RECORD_SCORE)) {
				String score = getParameter("score");
				readRecord.setScore(score);
			}
			readRecord.setBookId(bookId);
			readRecord.setUserId(userId);
			readRecord.setType(type);
			readRecord.setUserName(user1.getName());
			readRecord.setUserFace(user1.getFace());
			readRecord.setBookName(book.getBookName());
			readRecord.setBookImage(book.getImages());
			readRecord.setCreateTime(TimeHelper.getCurrentTime());
			readRecordService.save(readRecord);
		}
		// 猜你喜欢
		List randList = bookService.randomBooks("5");
		// 谁读这本书
		List<ReadRecord> readList = readRecordService.searchReadRecordByParam(
				"", bookId, "", "5");
		Object rand = bookDoubanService.findScoreByBookId(bookId);
		//查询书籍评论
		List<ReadRecord> readComment = readRecordService.searchReadRecordByParam(
				"", bookId, Constants.RECORD_COMMENT, "5");
		
		getRequest().setAttribute("rand", rand);
		getRequest().setAttribute("randList", randList);
		getRequest().setAttribute("readList", readList);
		getRequest().setAttribute("recordType", type);
		getRequest().setAttribute("user", user);
		getRequest().setAttribute("readComment", readComment);
		getRequest().setAttribute("readCommentSize", readComment.size());
		getRequest().setAttribute("book", book);
		return "view1";
	}

	/**
	 * TODO(模糊搜索)
	 * @return
	 * @throws Exception
	*/
	public String searchKey() throws Exception {
		String keyword = getParameter("s");
		if(!StringHelper.isContainsChinese(keyword)){
			keyword=new String(keyword.getBytes("iso8859-1"),"UTF-8");
		}
		System.out.println(keyword);
		pager = this.getPage();
		savePage(bookService.searchBooksByKeyword(pager, keyword));
		getRequest().setAttribute("user", user);
		return "search";
	}
	
	/**
	 * TODO(提交评论)
	 * @return
	 * @throws Exception
	*/
	public String comment() throws Exception{
		ReadRecord readRecord = new ReadRecord();
		String bookId = getParameter("bookIdB");
		String userId = getParameter("userIdB");
//		String pltitle = getParameter("pltitle");
		//评论内容
		String plinfo = getParameter("plinfoB");
		String type = getParameter("typeB");
		book = bookService.getBookById(bookId);
		
		readRecord.setBookId(bookId);
		readRecord.setUserId(userId);
		readRecord.setType(type);
		readRecord.setContent(plinfo);
		readRecord.setUserName(user.getName());
		readRecord.setUserFace(user.getFace());
		readRecord.setBookName(book.getBookName());
		readRecord.setBookImage(book.getImages());
		readRecord.setCreateTime(TimeHelper.getCurrentTime());
		readRecordService.save(readRecord);
		
		book = bookService.getBookById(bookId);
		// 猜你喜欢
		List randList = bookService.randomBooks("5");
		// 谁读这本书
		List<ReadRecord> readList = readRecordService.searchReadRecordByParam(
				"", bookId, "", "5");
		Object rand = bookDoubanService.findScoreByBookId(bookId);
		//查询书籍评论
		List<ReadRecord> readComment = readRecordService.searchReadRecordByParam(
				"", bookId, Constants.RECORD_COMMENT, "5");
		
		getRequest().setAttribute("rand", rand);
		getRequest().setAttribute("randList", randList);
		getRequest().setAttribute("readList", readList);
		getRequest().setAttribute("user", user);
		getRequest().setAttribute("readComment", readComment);
		getRequest().setAttribute("readCommentSize", readComment.size());
		getRequest().setAttribute("book", book);
		return "view1";
	}

	@Override
	public String delete() throws Exception {
		return LIST;
	}

	@Override
	public Book getModel() {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(id)) {
			book = bookService.getBookById(id);
		} else {
			book = new Book();
		}
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
