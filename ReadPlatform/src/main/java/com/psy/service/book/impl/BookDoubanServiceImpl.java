/**
 * BookDoubanServiceImpl.java
 * com.psy.service.book.impl
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月3日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.service.book.impl;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psy.entity.BookDouban;
import com.psy.service.book.BookDoubanService;
import com.psy.util.Constants;
import com.psy.util.JdbcDAO;
import com.psy.util.ServiceException;
import com.psy.util.StringHelper;

@Repository("bookDoubanService")
@Transactional
public class BookDoubanServiceImpl implements BookDoubanService{
	
	@Autowired
	private JdbcDAO jdbcDAO;

	@Override
	public BookDouban getBookInfoByIsbn(String isbn) throws Exception{
		BookDouban bookDouban = new BookDouban();
		String json = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(Constants.ISBN_URL+isbn);
			InputStreamReader isr = new InputStreamReader(url.openStream());
			char[] buffer = new char[1024];
			int len = 0;
			while((len=isr.read(buffer))!=-1){
				sb.append(buffer,0,len);
			}
			json = sb.toString();
			if(StringUtils.isNotEmpty(json)){
				bookDouban = analyzJson(json);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bookDouban;
	}
	
	private static BookDouban analyzJson(String json) {
		BookDouban bookDouban = new BookDouban();
		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject bookObj = (JSONObject) jsonParser.nextValue();
		JSONObject rating = bookObj.getJSONObject("rating");
		JSONArray tags = bookObj.getJSONArray("tags");
		JSONObject images = bookObj.getJSONObject("images");
		//书名
		bookDouban.setBookName(bookObj.getString("title"));
		//副标题
		bookDouban.setSubTitle(bookObj.getString("subtitle"));
		//作者
		bookDouban.setAuthor(bookObj.getString("author"));
		//isbn10
		bookDouban.setIsbn10(bookObj.getString("isbn10"));
		//isbn13
		bookDouban.setIsbn13(bookObj.getString("isbn13"));
		//url
		bookDouban.setUrl(bookObj.getString("url"));
		//alt
		bookDouban.setAlt(bookObj.getString("alt"));
		//image
		bookDouban.setImage(bookObj.getString("image"));
		//small_images
		bookDouban.setSmallImage(images.getString("small"));
		//large_images
		bookDouban.setLargeImage(images.getString("large"));
		//medium_images
		bookDouban.setMediumImage(images.getString("medium"));
		//译者
		bookDouban.setTranslator(bookObj.getString("translator"));
		//出版社
		bookDouban.setPublisher(bookObj.getString("publisher"));
		//出版日期
		bookDouban.setPubdate(bookObj.getString("pubdate"));
		//价钱
		bookDouban.setPrice(bookObj.getString("price"));
		//总页数
		bookDouban.setPages(rating.getString("numRaters"));
		//本书豆瓣通用标签
		String tagsAll = "";
		if(tags.size()>0){
			for(int i = 0;i<tags.size();i++){
				tagsAll += tags.getJSONObject(i).getString("name") +",";
			}
			System.out.println(tagsAll.substring(0, tagsAll.length()-1));
			bookDouban.setCommonTags(tagsAll.substring(0, tagsAll.length()-1));
		}
		//作者简介
		bookDouban.setAuthorIntro(bookObj.getString("author_intro"));
		//摘要
		bookDouban.setSummary(bookObj.getString("summary"));
		
		return bookDouban;
	}

	@Override
	public List findRandByType(String num,String type) throws ServiceException {
		String sql = "SELECT * FROM ";
		//阅读
		if(type.equals(Constants.RANK_READ)){
			sql += " `rank_read` LIMIT 0,";
		}else{
			sql += " `rank_score` LIMIT 0,";
		}
		sql += num;
		List rands = jdbcDAO.findForList(sql);
		return rands;
	}

	@Override
	public Object findScoreByBookId(String bookId) throws ServiceException {
		String sql = "SELECT * FROM  `rank_score` where id = "+bookId;
		List rands = jdbcDAO.findForList(sql);
		if(!StringHelper.isEmptyList(rands)){
			Object rand = rands.get(0);
			return rand;
		}
		return null;
	}

}
