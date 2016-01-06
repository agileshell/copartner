package com.insoul.ti.vo;

public class TutorListVO {

	private Long tutorId;

	private String name;

	private String mobile;

	private String avatar;

	private String domain;

	private String professionName;

	private String title; // 职务

	private String startupExp;// 创业经验

	private String managementExp;// 管理经验

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
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

}
