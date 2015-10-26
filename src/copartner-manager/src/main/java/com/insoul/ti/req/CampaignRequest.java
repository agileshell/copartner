package com.insoul.ti.req;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class CampaignRequest {

	@NotNull
	private String title;// 标题

	private MultipartFile coverImg;// 封皮

	private String contentVal;// 内容

	private String status;// 状态 active,inactive,deleted

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(MultipartFile coverImg) {
        this.coverImg = coverImg;
    }

    public String getContentVal() {
        return contentVal;
    }

    public void setContentVal(String contentVal) {
        this.contentVal = contentVal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}