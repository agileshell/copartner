package com.insoul.copartner.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.insoul.copartner.validate.constraint.StringLength;

public class CourseAddRequest implements Serializable {

    private static final long serialVersionUID = -7940493172865416312L;

    @NotBlank
    @StringLength(max = 20)
    private String name;// 课程名称

    @NotBlank
    @StringLength(max = 20)
    private String speaker;// 主讲人
    
    @NotBlank
    private String coverImg;// 封皮

    @NotBlank
    @StringLength(max = 50)
    private String synopsis;// 摘要,显示列表使用

    @NotBlank
    private String url;// 链接

    @NotNull
    private Integer time;// 时长, 单位:分钟

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}
