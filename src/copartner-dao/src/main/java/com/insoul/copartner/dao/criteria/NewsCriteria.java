package com.insoul.copartner.dao.criteria;

public class NewsCriteria extends PaginationCriteria {

    private Integer type;

    private String[] status;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String[] getStatus() {
        if (null == status || status.length <= 0) {
            status = new String[] { "active", "inactive" };
        }

        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }
}
