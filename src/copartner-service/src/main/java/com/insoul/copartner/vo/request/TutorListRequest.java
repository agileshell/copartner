package com.insoul.copartner.vo.request;

public class TutorListRequest extends PaginationRequest {

    private static final long serialVersionUID = 9168449284140340867L;

    private String keyword;

    private Long domainId;

    private Long from;

    private Long to;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
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
