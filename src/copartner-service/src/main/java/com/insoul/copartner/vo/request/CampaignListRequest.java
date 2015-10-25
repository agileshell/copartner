package com.insoul.copartner.vo.request;

public class CampaignListRequest extends PaginationRequest {

    private static final long serialVersionUID = 2851169942975916364L;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
