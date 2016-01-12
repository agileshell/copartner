package com.insoul.ti.req;

import org.springframework.web.multipart.MultipartFile;

public class TutorAddRequest {

	private String name;

	private MultipartFile avatar;

	private String mobile;

	private String introduction;

	private String title; // 职务

	private String startupExp;// 创业经验

	private String managementExp;// 管理经验

	private String domains;// 行业

	private String topic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
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

	public String getDomains() {
		return domains;
	}

	public void setDomains(String domains) {
		this.domains = domains;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
