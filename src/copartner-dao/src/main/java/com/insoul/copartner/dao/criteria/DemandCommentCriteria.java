package com.insoul.copartner.dao.criteria;

public class DemandCommentCriteria extends PaginationCriteria {

    private Long demandId;
    
    private String status;// 状态 active 可用，inactive不可用

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

}
