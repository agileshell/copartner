package com.insoul.copartner.vo;

import java.io.Serializable;

public class ProjectDetailVO implements Serializable {

    private static final long serialVersionUID = -9070163501160902318L;

    private long id;

    private String name;

    private String logo;

    private String content;

    private String status;

    private String projectPhase;

    private String location;

    private String industryDomain;

    private String teamSize;

    private String advantage;

    private Long likeCount;

    private Long commentCount;

    // private Set<UserLeanVO> likers;

    private String businessPlan;

    private FinancingDetailVO financing;

    private DemandDetailVO demand;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
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

    public String getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(String businessPlan) {
        this.businessPlan = businessPlan;
    }

    public FinancingDetailVO getFinancing() {
        return financing;
    }

    public void setFinancing(FinancingDetailVO financing) {
        this.financing = financing;
    }

    public DemandDetailVO getDemand() {
        return demand;
    }

    public void setDemand(DemandDetailVO demand) {
        this.demand = demand;
    }

}
