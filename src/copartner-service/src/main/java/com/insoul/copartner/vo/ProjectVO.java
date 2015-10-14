package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class ProjectVO implements Serializable {

    private static final long serialVersionUID = -4435427359292836901L;

    private long id;

    private String name;

    private String logo;

    private String advantage;

    private String projectPhase;

    private String location;

    private String industryDomain;

    private String teamSize;

    private Long likeCount;

    private Long commentCount;

    private UserLeanVO user;

    private Set<UserLeanVO> likers;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    private boolean isliked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(String projectPhase) {
        this.projectPhase = projectPhase;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustryDomain() {
        return industryDomain;
    }

    public void setIndustryDomain(String industryDomain) {
        this.industryDomain = industryDomain;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public UserLeanVO getUser() {
        return user;
    }

    public void setUser(UserLeanVO user) {
        this.user = user;
    }

    public Set<UserLeanVO> getLikers() {
        return likers;
    }

    public void setLikers(Set<UserLeanVO> likers) {
        this.likers = likers;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isIsliked() {
        return isliked;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

}
