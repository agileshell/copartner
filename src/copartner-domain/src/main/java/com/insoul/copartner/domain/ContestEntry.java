package com.insoul.copartner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 参赛项目
 */
@Entity
@Table(name = "contest_entry", catalog = "copartner")
@NamedQueries({
        @NamedQuery(name = "ContestEntry.getByContestAndUser", query = "FROM ContestEntry WHERE contestId = :contestId AND userId = :userId") })
public class ContestEntry extends BaseEntity {

    private static final long serialVersionUID = 1738371565002304322L;

    @Column(name = "project_id", nullable = false)
    private Long projectId;// 参赛项目

    @Column(name = "contest_id", nullable = false)
    private Long contestId;// 大赛ID

    @Column(name = "user_id", nullable = false)
    private Long userId;// 参赛人

    @Column(name = "status", nullable = false)
    private String status = "active";// 状态 active, inactive, deleted

    @Column(name = "votes", nullable = false)
    private Long votes = 0L;

    @Column(name = "tutor_votes", nullable = false)
    private Long tutorVotes = 0L; // 导师投票次数

    @Column(name = "investor_votes", nullable = false)
    private Long investorVotes = 0L;// 投资人投票次数

    @Column(name = "has_business_registered", nullable = false)
    private Boolean hasBusinessRegistered = false;// 是否工商注册

    @Column(name = "business_license")
    private String businessLicense;// 营业执照号

    @Column(name = "business_license_img")
    private String businessLicenseImg;// 营业执照

    @Column(name = "location")
    private String location;// 所在市县或园区

    @Column(name = "instance")
    private String instance;// 创业实体名称

    @Column(name = "industry")
    private String industry;// 所属行业

    @Column(name = "legal_formation")
    private String legalFormation;// 企业法律形态

    @Column(name = "employqty")
    private Integer employqty;// 吸纳就业人数

    @Column(name = "regtime")
    private String regtime;// 注册时间

    @Column(name = "legal_person")
    private String legalPerson;// 法定代表人

    @Column(name = "user_category")
    private String userCategory;// 人员类别

    @Column(name = "contact")
    private String contact;// 联系方式

    @Column(name = "id_number")
    private String idNumber;// 身份证

    @Column(name = "bank_name")
    private String bankName;// 开户行

    @Column(name = "bank_user_name")
    private String bankUserName;// 开户名

    @Column(name = "bank_account")
    private String bankAccount;// 账号

    @Column(name = "support_money")
    private String supportMoney;// 申请扶持金额

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getTutorVotes() {
        return tutorVotes;
    }

    public void setTutorVotes(Long tutorVotes) {
        this.tutorVotes = tutorVotes;
    }

    public Long getInvestorVotes() {
        return investorVotes;
    }

    public void setInvestorVotes(Long investorVotes) {
        this.investorVotes = investorVotes;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
        result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
        result = prime * result + ((bankUserName == null) ? 0 : bankUserName.hashCode());
        result = prime * result + ((businessLicense == null) ? 0 : businessLicense.hashCode());
        result = prime * result + ((businessLicenseImg == null) ? 0 : businessLicenseImg.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((contestId == null) ? 0 : contestId.hashCode());
        result = prime * result + ((employqty == null) ? 0 : employqty.hashCode());
        result = prime * result + ((hasBusinessRegistered == null) ? 0 : hasBusinessRegistered.hashCode());
        result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
        result = prime * result + ((industry == null) ? 0 : industry.hashCode());
        result = prime * result + ((instance == null) ? 0 : instance.hashCode());
        result = prime * result + ((investorVotes == null) ? 0 : investorVotes.hashCode());
        result = prime * result + ((legalFormation == null) ? 0 : legalFormation.hashCode());
        result = prime * result + ((legalPerson == null) ? 0 : legalPerson.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
        result = prime * result + ((regtime == null) ? 0 : regtime.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((supportMoney == null) ? 0 : supportMoney.hashCode());
        result = prime * result + ((tutorVotes == null) ? 0 : tutorVotes.hashCode());
        result = prime * result + ((userCategory == null) ? 0 : userCategory.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((votes == null) ? 0 : votes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContestEntry other = (ContestEntry) obj;
        if (bankAccount == null) {
            if (other.bankAccount != null)
                return false;
        } else if (!bankAccount.equals(other.bankAccount))
            return false;
        if (bankName == null) {
            if (other.bankName != null)
                return false;
        } else if (!bankName.equals(other.bankName))
            return false;
        if (bankUserName == null) {
            if (other.bankUserName != null)
                return false;
        } else if (!bankUserName.equals(other.bankUserName))
            return false;
        if (businessLicense == null) {
            if (other.businessLicense != null)
                return false;
        } else if (!businessLicense.equals(other.businessLicense))
            return false;
        if (businessLicenseImg == null) {
            if (other.businessLicenseImg != null)
                return false;
        } else if (!businessLicenseImg.equals(other.businessLicenseImg))
            return false;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
        if (contestId == null) {
            if (other.contestId != null)
                return false;
        } else if (!contestId.equals(other.contestId))
            return false;
        if (employqty == null) {
            if (other.employqty != null)
                return false;
        } else if (!employqty.equals(other.employqty))
            return false;
        if (hasBusinessRegistered == null) {
            if (other.hasBusinessRegistered != null)
                return false;
        } else if (!hasBusinessRegistered.equals(other.hasBusinessRegistered))
            return false;
        if (idNumber == null) {
            if (other.idNumber != null)
                return false;
        } else if (!idNumber.equals(other.idNumber))
            return false;
        if (industry == null) {
            if (other.industry != null)
                return false;
        } else if (!industry.equals(other.industry))
            return false;
        if (instance == null) {
            if (other.instance != null)
                return false;
        } else if (!instance.equals(other.instance))
            return false;
        if (investorVotes == null) {
            if (other.investorVotes != null)
                return false;
        } else if (!investorVotes.equals(other.investorVotes))
            return false;
        if (legalFormation == null) {
            if (other.legalFormation != null)
                return false;
        } else if (!legalFormation.equals(other.legalFormation))
            return false;
        if (legalPerson == null) {
            if (other.legalPerson != null)
                return false;
        } else if (!legalPerson.equals(other.legalPerson))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        if (regtime == null) {
            if (other.regtime != null)
                return false;
        } else if (!regtime.equals(other.regtime))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (supportMoney == null) {
            if (other.supportMoney != null)
                return false;
        } else if (!supportMoney.equals(other.supportMoney))
            return false;
        if (tutorVotes == null) {
            if (other.tutorVotes != null)
                return false;
        } else if (!tutorVotes.equals(other.tutorVotes))
            return false;
        if (userCategory == null) {
            if (other.userCategory != null)
                return false;
        } else if (!userCategory.equals(other.userCategory))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (votes == null) {
            if (other.votes != null)
                return false;
        } else if (!votes.equals(other.votes))
            return false;
        return true;
    }

}
