package com.psy.entity;

// Generated 2015-6-3 10:36:41 by Hibernate Tools 3.2.1.GA

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
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users")
public class Users implements java.io.Serializable {

	private String userId;
	private String name;
	private Integer readNum;
	private String grade;
	private String classes;
	private String school;
	private String face;
	private String sex;
	private String phone;
	private String email;
	private String type;
	private String address;
	private String background;
	private String createTime;

	public Users() {
	}

	public Users(String name, Integer readNum, String grade, String classes,
			String school, String face, String sex, String phone, String email,
			String type, String address, String background, String createTime) {
		this.name = name;
		this.readNum = readNum;
		this.grade = grade;
		this.classes = classes;
		this.school = school;
		this.face = face;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.type = type;
		this.address = address;
		this.background = background;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "read_num")
	public Integer getReadNum() {
		return this.readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	@Column(name = "grade")
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "classes")
	public String getClasses() {
		return this.classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	@Column(name = "school")
	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "face")
	public String getFace() {
		return this.face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	@Column(name = "sex")
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "background")
	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	@Column(name = "create_time")
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
