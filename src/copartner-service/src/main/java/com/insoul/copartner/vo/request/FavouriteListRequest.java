package com.insoul.copartner.vo.request;

public class FavouriteListRequest extends PaginationRequest {

    private static final long serialVersionUID = -2597731249471815080L;

    private Long from;

    private Long to;

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
