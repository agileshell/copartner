package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.ProjectCriteria;
import com.insoul.copartner.domain.Project;

public interface IProjectDao extends IBaseDao<Project, Long> {

    List<Project> queryProject(ProjectCriteria criteria);

    Long countProject(ProjectCriteria criteria);

	long count();
}
