package com.insoul.copartner.vo.request;

import java.io.Serializable;

public class PaginationRequest implements Serializable {

    private static final long serialVersionUID = 1201389992604376408L;

    private Integer offset;

    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (null == limit || limit <= 0) {
            this.limit = 10;
        } else {
            this.limit = limit;
        }
    }

}
