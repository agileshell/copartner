package com.insoul.ti.req;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class CourseRequest {
	
	@NotNull
	private String name;// 课程名称

    @NotNull
    private String speaker;// 主讲人

    @NotNull
    private String synopsis;// 摘要,显示列表使用
    
    private MultipartFile coverImg;// 封皮

    private String media;// 视频

    @NotNull
    private Integer time;// 时长, 单位:分钟

    @NotNull
    private String status;// 状态 active,inactive,deleted

    @NotNull
    private int free; // 是否免费

    public MultipartFile getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(MultipartFile coverImg) {
        this.coverImg = coverImg;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }
}