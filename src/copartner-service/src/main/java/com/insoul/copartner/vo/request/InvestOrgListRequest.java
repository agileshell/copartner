package com.insoul.copartner.vo.request;

public class InvestOrgListRequest extends PaginationRequest {

    private static final long serialVersionUID = 2851169942975916364L;

    private Long from;

    private Long to;
    
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}