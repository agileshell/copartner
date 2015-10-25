package com.insoul.copartner.dao.criteria;

public class CampaignCriteria extends PaginationCriteria {

    private Long id;
    
    private String title;// 标题
    
    private String[] status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

}
