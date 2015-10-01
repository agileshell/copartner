package com.insoul.ti.vo;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年7月28日 下午5:00:49
 */
public class DemandVO extends BaseVO {

    private Long userId;// 创建者，用户ID

    private String userName;// 创建者

    private String projectName;// 项目名称

    private String status = "active";// 状态 active 可用，inactive不可用

    private String advantage;// 优势

    private String content;// 实施条件

    private String teamSizeName;// 团队规模

    private String fullLocation;// 地区缓存

    private String industryDomainName;// 行业

    private Long likeCount = 0L;// 收藏次数

    private Long commentCount = 0L;// 评论次数

    private String contactPerson;// 联系人

    private String contact;// 联系方式

    private Boolean hasBusinessRegistered = false;// 是否工商注册
    private String reward;// 回报
    private Long projectId;// 关联的项目编号

    private String businessLicense;// 营业执照号

    private String businessPlan;// 商业计划书

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(String businessPlan) {
        this.businessPlan = businessPlan;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
