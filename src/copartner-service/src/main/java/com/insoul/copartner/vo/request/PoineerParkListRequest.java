package com.insoul.copartner.vo.request;

public class PoineerParkListRequest extends PaginationRequest {

    private static final long serialVersionUID = 2851169942975916364L;

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}