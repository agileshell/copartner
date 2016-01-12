package com.insoul.copartner.vo;

import java.io.Serializable;

public class TutorDetailVO implements Serializable {

	private static final long serialVersionUID = -7843640491980037840L;

	private Long tutorId;

	private String name;

	private String avatar;

	private IndustryDomainVO domain;

	private String email;

	private String mobile;

	private String introduction;

	private String title; // 职务

	private String startupExp;// 创业经验

	private String managementExp;// 管理经验

	private String topic; // 话题

	private String price;// 价格

	private int minutes;// 分钟

	private int orderCount;// 预约人数

	private int likeCount;// 赞人数

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public IndustryDomainVO getDomain() {
		return domain;
	}

	public void setDomain(IndustryDomainVO domain) {
		this.domain = domain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartupExp() {
		return startupExp;
	}

	public void setStartupExp(String startupExp) {
		this.startupExp = startupExp;
	}

	public String getManagementExp() {
		return managementExp;
	}

	public void setManagementExp(String managementExp) {
		this.managementExp = managementExp;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

}
