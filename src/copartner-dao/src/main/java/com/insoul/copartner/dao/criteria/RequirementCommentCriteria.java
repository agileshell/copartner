package com.insoul.copartner.dao.criteria;

public class RequirementCommentCriteria extends PaginationCriteria {

    private Long requirementId;

    private String status;// 状态 active 可用，inactive不可用

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
