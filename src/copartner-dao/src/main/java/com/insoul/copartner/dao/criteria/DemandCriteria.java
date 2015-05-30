package com.insoul.copartner.dao.criteria;


public class DemandCriteria extends PaginationCriteria {

    private Long userId;

    private Integer type;

    private String status[];

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

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

}
