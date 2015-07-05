package com.insoul.copartner.vo.request;

import javax.validation.constraints.NotNull;

public class NewsListRequest extends PaginationRequest {

    private static final long serialVersionUID = 1034389330223073602L;

    @NotNull
    private Integer type = 1; // 默认 1行业

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
