package com.psy.entity;

// Generated 2015-6-8 18:07:14 by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BooksCollection generated by hbm2java
 */
@Entity
@Table(name = "books_collection")
public class BooksCollection implements java.io.Serializable {

	private String id;
	private String bookId;
	private String userId;
	private String bookName;
	private String author;
	private String publisher;
	private String images;
	private String smallImages;
	private String largeImages;
	private String mediumImages;
	private String createTime;

	public BooksCollection() {
	}

	public BooksCollection(String bookId, String userId, String bookName,
			String author, String publisher, String images, String smallImages,
			String largeImages, String mediumImages,String createTime) {
		this.bookId = bookId;
		this.userId = userId;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.images = images;
		this.smallImages = smallImages;
		this.largeImages = largeImages;
		this.mediumImages = mediumImages;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "book_id")
	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "book_name")
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name = "author")
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "publisher")
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "images")
	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	@Column(name = "small_images")
	public String getSmallImages() {
		return this.smallImages;
	}

	public void setSmallImages(String smallImages) {
		this.smallImages = smallImages;
	}

	@Column(name = "large_images")
	public String getLargeImages() {
		return this.largeImages;
	}

	public void setLargeImages(String largeImages) {
		this.largeImages = largeImages;
	}

	@Column(name = "medium_images")
	public String getMediumImages() {
		return this.mediumImages;
	}

	public void setMediumImages(String mediumImages) {
		this.mediumImages = mediumImages;
	}

	@Column(name = "create_time", length = 30)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
