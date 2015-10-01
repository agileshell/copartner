package com.insoul.copartner.vo.request;

public class ContestEntryListRequest extends PaginationRequest {

    private static final long serialVersionUID = 2851169942975916364L;

    private Long from;

    private Long to;

    private String keyword;
    
    private Long contestId;// 参与的大赛ID

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
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
