package com.insoul.copartner.vo.request;

import java.io.Serializable;

public class DemandListRequest extends PaginationRequest implements Serializable {

    private static final long serialVersionUID = -7650378463719813280L;

    private Long userId;

    private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
