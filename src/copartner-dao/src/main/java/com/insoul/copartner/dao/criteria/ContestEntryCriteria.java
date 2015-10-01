package com.insoul.copartner.dao.criteria;

import java.util.Date;

public class ContestEntryCriteria extends PaginationCriteria {

    private String name;// 参赛项目名称
    
    private Long contestId;// 参与的大赛ID

    private Date from;

    private Date to;

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}