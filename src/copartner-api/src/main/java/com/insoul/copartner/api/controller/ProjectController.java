package com.insoul.copartner.api.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IProjectService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.PaginationRequest;
import com.insoul.copartner.vo.request.ProjectAddRequest;
import com.insoul.copartner.vo.request.ProjectCommentRequest;
import com.insoul.copartner.vo.request.ProjectListRequest;
import com.insoul.copartner.vo.request.ProjectUpdateRequest;

@Controller
public class ProjectController extends BaseController {

    @Resource
    private IProjectService projectService;

    @RequestMapping(value = "/user/{userId}/projects", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listProjects(@PathVariable Long userId,
            @Valid ProjectListRequest requestData, BindingResult result) {
        requestData.setUserId(userId);

        return ResponseUtil.jsonSucceed(projectService.listProjects(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listProjects(@Valid ProjectListRequest requestData, BindingResult result)
            throws DataValidationException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(projectService.listProjects(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createProject(@Valid ProjectAddRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        projectService.createProject(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getDemand(@PathVariable Long projectId) throws CException {
        return ResponseUtil.jsonSucceed(projectService.getProject(projectId), HttpStatus.OK);
    }

    @RequestMapping(value = "/project/{projectId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateProject(@PathVariable Long projectId,
            @Valid ProjectUpdateRequest requestData, BindingResult result) throws CException {
        if (null == projectId || projectId <= 0 || result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        requestData.setProjectId(projectId);
        projectService.updateProject(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/{projectId}/like", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> likeProject(@PathVariable Long projectId) throws CException {
        projectService.likeProject(projectId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/{projectId}/like", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> unlikeProject(@PathVariable Long projectId) throws CException {
        projectService.unlikeProject(projectId);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/{projectId}/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentProject(@PathVariable Long projectId,
            @Valid ProjectCommentRequest requestData, BindingResult result) throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        projectService.commentProject(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/project/{projectId}/comments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listComments(@PathVariable Long projectId, PaginationRequest requestData)
            throws CException {
        if (null == projectId || projectId <= 0) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(projectService.listComments(projectId, requestData), HttpStatus.OK);
    }
}
