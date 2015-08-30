package com.insoul.ti.vo;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年7月28日 下午5:00:49
 */
public class FinanceVO extends BaseVO {
	
	private Long userId;// 创建者，用户ID
	
	private String userName;// 创建者

    private String name;// 项目名称

    private String status = "active";// 状态 active 可用，inactive不可用

    private String advantage;// 优势

    private String content;// 实施条件
    
    private String teamSizeName;// 团队规模

    private String fullLocation;// 地区缓存
    
    private String industryDomainName;// 行业

    private String contactPerson;// 联系人

    private String contact;// 联系方式
    
    private String financingPhaseName;// 融资阶段
    
    private Boolean hasBusinessRegistered = false;// 是否工商注册
    private String reward;// 回报

	public String getFinancingPhaseName() {
		return financingPhaseName;
	}

	public void setFinancingPhaseName(String financingPhaseName) {
		this.financingPhaseName = financingPhaseName;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Boolean getHasBusinessRegistered() {
		return hasBusinessRegistered;
	}

	public void setHasBusinessRegistered(Boolean hasBusinessRegistered) {
		this.hasBusinessRegistered = hasBusinessRegistered;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTeamSizeName() {
		return teamSizeName;
	}

	public void setTeamSizeName(String teamSizeName) {
		this.teamSizeName = teamSizeName;
	}

	public String getFullLocation() {
		return fullLocation;
	}

	public void setFullLocation(String fullLocation) {
		this.fullLocation = fullLocation;
	}

	public String getIndustryDomainName() {
		return industryDomainName;
	}

	public void setIndustryDomainName(String industryDomainName) {
		this.industryDomainName = industryDomainName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}