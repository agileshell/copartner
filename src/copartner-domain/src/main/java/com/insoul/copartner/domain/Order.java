package com.insoul.copartner.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "order", catalog = "copartner")
@NamedQueries({ @NamedQuery(name = "Order.findByUserId", query = "FROM Order WHERE userId = :userId") })
public class Order extends BaseEntity {

	private static final long serialVersionUID = -5004044823118983437L;

	@Column(name = "user_id", nullable = false)
	private Long userId; // 订单拥有者

	@Column(name = "tutor_id", nullable = false)
	private Long tutorId; // 导师

	@Column(name = "user_name", nullable = false)
	private String userName;// 用户姓名

	@Column(name = "user_mobile", nullable = false)
	private String userMobile;// 用户手机号码

	@Column(name = "user_question", nullable = false)
	private String userQuestion;// 用户问题描述

	@Column(nullable = false)
	private Integer minutes = 0;// 时间

	@Column(name = "payment_type")
	private String paymentType;// 支付方式

	@Column(name = "amount", nullable = false, precision = 15, scale = 4)
	private BigDecimal amount;// 总金额

	@Column(nullable = false)
	private String status = "new"; // paid/canceled/used

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((minutes == null) ? 0 : minutes.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tutorId == null) ? 0 : tutorId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userMobile == null) ? 0 : userMobile.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userQuestion == null) ? 0 : userQuestion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (minutes == null) {
			if (other.minutes != null)
				return false;
		} else if (!minutes.equals(other.minutes))
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tutorId == null) {
			if (other.tutorId != null)
				return false;
		} else if (!tutorId.equals(other.tutorId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userMobile == null) {
			if (other.userMobile != null)
				return false;
		} else if (!userMobile.equals(other.userMobile))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userQuestion == null) {
			if (other.userQuestion != null)
				return false;
		} else if (!userQuestion.equals(other.userQuestion))
			return false;
		return true;
	}

}
