package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class OrderCreateRequest implements Serializable {

	private static final long serialVersionUID = -4624115012504833269L;

	@NotNull
	private Long tutorId; // 导师

	@NotBlank
	private String userName;// 用户姓名

	@NotBlank
	private String userMobile;// 用户手机号码

	@NotBlank
	private String userQuestion;// 用户问题描述

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}

}
