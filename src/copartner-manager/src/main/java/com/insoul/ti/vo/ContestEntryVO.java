package com.insoul.ti.vo;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月13日 上午11:21:46
 */
public class ContestEntryVO extends BaseVO {

    private Long projectId;// 参赛项目

    private String projectName;

    private Long contestId;// 大赛ID

    private String contestName;

    private Long userId;// 参赛人

    private String userName;

    private String status = "active";// 状态 active, inactive, deleted

    private Long votes = 0L;

    private Boolean hasBusinessRegistered = false;// 是否工商注册

    private String businessLicense;// 营业执照号

    private String businessLicenseImg;// 营业执照
    
    private String location;// 所在市县或园区

    private String instance;// 创业实体名称

    private String industry;// 所属行业

    private String legalFormation;// 企业法律形态

    private Integer employqty;// 吸纳就业人数

    private String regtime;// 注册时间

    private String legalPerson;// 法定代表人

    private String userCategory;// 人员类别

    private String contact;// 联系方式

    private String idNumber;// 身份证

    private String bankName;// 开户行

    private String bankUserName;// 开户名

    private String bankAccount;// 账号

    private String supportMoney;// 申请扶持金额

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLegalFormation() {
        return legalFormation;
    }

    public void setLegalFormation(String legalFormation) {
        this.legalFormation = legalFormation;
    }

    public Integer getEmployqty() {
        return employqty;
    }

    public void setEmployqty(Integer employqty) {
        this.employqty = employqty;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(String supportMoney) {
        this.supportMoney = supportMoney;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Boolean getHasBusinessRegistered() {
        return hasBusinessRegistered;
    }

    public void setHasBusinessRegistered(Boolean hasBusinessRegistered) {
        this.hasBusinessRegistered = hasBusinessRegistered;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }
}
