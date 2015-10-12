package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.RequirementCommentCriteria;
import com.insoul.copartner.domain.RequirementComments;

public interface IRequirementCommentsDao extends IBaseDao<RequirementComments, Long> {

    List<RequirementComments> queryComments(RequirementCommentCriteria criteria);

    Long countComments(RequirementCommentCriteria criteria);
}
