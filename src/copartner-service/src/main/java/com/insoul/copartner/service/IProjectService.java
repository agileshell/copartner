package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ProjectVO;
import com.insoul.copartner.vo.request.ProjectAddRequest;
import com.insoul.copartner.vo.request.ProjectListRequest;

public interface IProjectService {

    Pagination<ProjectVO> listProjects(ProjectListRequest requestData);

    void createProject(ProjectAddRequest requestData) throws CException;

    void likeProject(Long projectId) throws CException;

    void unlikeProject(Long projectId) throws CException;
}
