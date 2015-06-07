package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.CommentVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.ProjectDetailVO;
import com.insoul.copartner.vo.ProjectVO;
import com.insoul.copartner.vo.request.PaginationRequest;
import com.insoul.copartner.vo.request.ProjectAddRequest;
import com.insoul.copartner.vo.request.ProjectCommentRequest;
import com.insoul.copartner.vo.request.ProjectListRequest;
import com.insoul.copartner.vo.request.ProjectUpdateRequest;

public interface IProjectService {

    Pagination<ProjectVO> listProjects(ProjectListRequest requestData);

    void createProject(ProjectAddRequest requestData) throws CException;

    void likeProject(Long projectId) throws CException;

    void unlikeProject(Long projectId) throws CException;

    void commentProject(ProjectCommentRequest requestData) throws CException;

    Pagination<CommentVO> listComments(Long projectId, PaginationRequest requestData);

    ProjectDetailVO getProject(Long projectId) throws CException;

    void updateProject(ProjectUpdateRequest requestData) throws CException;
}
