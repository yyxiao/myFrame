package com.psy.entity;

// Generated 2015-6-3 10:27:45 by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReadScore generated by hbm2java
 */
@Entity
@Table(name = "read_score")
public class ReadScore implements java.io.Serializable {

	private Integer id;
	private Integer bookId;
	private Integer userId;
	private String record;
	private String createTime;

	public ReadScore() {
	}

	public ReadScore(Integer bookId, Integer userId, String record,
			String createTime) {
		this.bookId = bookId;
		this.userId = userId;
		this.record = record;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "book_id")
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "record")
	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Column(name = "create_time", length = 30)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
