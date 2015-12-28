package com.insoul.copartner.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class OrderVO implements Serializable {

	private static final long serialVersionUID = -5735885586421151656L;

	private Long id;

	private Long tutorId;// 导师

	private String tutorName;

	private String tutorAvatar;

	private String userName;// 用户姓名

	private String userMobile;// 用户手机号码

	private String userQuestion;// 用户问题描述

	private Integer minutes;// 时间

	private String paymentType;// 支付方式

	private BigDecimal amount;// 总金额

	private String status;

	@JsonSerialize(using = CustomDateSerializer.class)
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	public String getTutorAvatar() {
		return tutorAvatar;
	}

	public void setTutorAvatar(String tutorAvatar) {
		this.tutorAvatar = tutorAvatar;
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

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
