package com.insoul.copartner.dao.criteria;

public class CampaignCriteria extends PaginationCriteria {

    private String[] status;

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

}
