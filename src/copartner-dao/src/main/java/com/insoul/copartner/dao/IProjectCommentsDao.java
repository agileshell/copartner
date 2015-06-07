package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.ProjectCommentCriteria;
import com.insoul.copartner.domain.ProjectComments;

public interface IProjectCommentsDao extends IBaseDao<ProjectComments, Long> {

    List<ProjectComments> queryComments(ProjectCommentCriteria criteria);

    Long countComments(ProjectCommentCriteria criteria);
}
