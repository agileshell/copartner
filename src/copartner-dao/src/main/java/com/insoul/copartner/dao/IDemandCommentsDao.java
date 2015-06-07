package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.DemandCommentCriteria;
import com.insoul.copartner.domain.DemandComments;

public interface IDemandCommentsDao extends IBaseDao<DemandComments, Long> {

    List<DemandComments> queryComments(DemandCommentCriteria criteria);

    Long countComments(DemandCommentCriteria criteria);
}
