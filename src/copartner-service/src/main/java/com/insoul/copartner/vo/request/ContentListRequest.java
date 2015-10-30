package com.insoul.copartner.vo.request;

public class ContentListRequest extends PaginationRequest {

    private static final long serialVersionUID = 2851169942975916364L;

    private Long from;

    private Long to;

    private String keyword;

    private Byte type;// 1:国家 2:地方

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
