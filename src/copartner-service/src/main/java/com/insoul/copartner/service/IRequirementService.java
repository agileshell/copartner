package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.CommentVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.RequirementDetailVO;
import com.insoul.copartner.vo.RequirementVO;
import com.insoul.copartner.vo.request.PaginationRequest;
import com.insoul.copartner.vo.request.RequirementAddRequest;
import com.insoul.copartner.vo.request.RequirementCommentRequest;
import com.insoul.copartner.vo.request.RequirementListRequest;

public interface IRequirementService {

    Pagination<RequirementVO> listRequirements(RequirementListRequest requestData);

    RequirementDetailVO getRequirement(Long requirementId) throws CException;

    void createRequirement(RequirementAddRequest requestData) throws CException;

    void deleteRequirement(Long requirementId) throws CException;

    void commentRequirement(RequirementCommentRequest requestData) throws CException;

    void likeRequirement(Long requirementId) throws CException;

    void unlikeRequirement(Long requirementId) throws CException;

    Pagination<CommentVO> listComments(Long requirementId, PaginationRequest requestData);
}
