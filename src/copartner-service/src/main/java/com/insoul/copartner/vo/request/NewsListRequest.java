package com.insoul.copartner.vo.request;

import javax.validation.constraints.NotNull;

public class NewsListRequest extends PaginationRequest {

    private static final long serialVersionUID = 1034389330223073602L;

    @NotNull
    private Integer type = 1; // 默认 1行业

    private Long from;

    private Long to;

    private String keyword;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
