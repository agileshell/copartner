package com.insoul.copartner.dao;

import com.insoul.copartner.domain.ProjectLikers;
import com.insoul.copartner.domain.ProjectLikersId;

public interface IProjectLikersDao extends IBaseDao<ProjectLikers, ProjectLikersId> {

    ProjectLikers findByIds(Long projectId, Long userId);
}
