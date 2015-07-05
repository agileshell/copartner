package com.insoul.copartner.vo.request;

import javax.validation.constraints.NotNull;

public class ContentListRequest extends PaginationRequest {

    private static final long serialVersionUID = 2851169942975916364L;

    @NotNull
    private Integer type = 1; // 默认 1政策解读

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
