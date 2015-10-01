package com.insoul.ti.req;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class ProjectRequest {

	@NotNull
	private String name;// 项目名称

    private MultipartFile logo;// 项目LOGO

    private String status = "active";// 状态 active 可用，inactive不可用, deleted删除

    private String advantage;// 优势

    private String content;// 实施条件
    
    private String contactPerson;// 联系人

    private String contact;// 联系方式

    private MultipartFile businessPlan;// 商业计划书

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
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

    public MultipartFile getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(MultipartFile businessPlan) {
        this.businessPlan = businessPlan;
    }
}