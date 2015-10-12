package com.insoul.copartner.vo.request;

public class ContestEntryListRequest extends PaginationRequest {

    private static final long serialVersionUID = 2851169942975916364L;

    private Long from;

    private Long to;

    private Long contestId;

    private Long userId;

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

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
