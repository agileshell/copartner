package com.insoul.copartner.dao;

import java.util.List;
import java.util.Set;

import com.insoul.copartner.dao.criteria.PaginationCriteria;
import com.insoul.copartner.domain.ProjectLikers;
import com.insoul.copartner.domain.ProjectLikersId;

public interface IProjectLikersDao extends IBaseDao<ProjectLikers, ProjectLikersId> {

    ProjectLikers findByIds(Long projectId, Long userId);

    List<ProjectLikers> findByProjectId(Long projectId);

    List<ProjectLikers> findByProjectIdsAndPagination(Set<Long> projectIds, PaginationCriteria pagination);
}
