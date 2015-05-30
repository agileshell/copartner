package com.insoul.copartner.dao.impl;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.domain.Project;

@Repository
public class ProjectDaoImpl extends BaseDaoImpl<Project, Long> implements IProjectDao {

}
