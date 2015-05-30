package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.List;

public class Pagination<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 5088210437888026778L;

    private List<T> list;

    private Long count;

    public Pagination() {
    }

    public Pagination(List<T> list, Long count) {
        this.list = list;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
