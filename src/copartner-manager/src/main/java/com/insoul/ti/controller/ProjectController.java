package com.insoul.ti.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.ProjectCriteria;
import com.insoul.copartner.domain.Project;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ProjectListRequest;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends WebBase {

	private static final String PROJECT_LIST = "project_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid ProjectListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(PROJECT_LIST, request);
		PageQuery query = request.init().getQuery();
		ProjectCriteria criteria = new ProjectCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setId(request.getId());
		criteria.setContent(request.getContent());
		criteria.setStatus(new String[] { request.getStatus() });
		criteria.setName(request.getName());
		List<Project> list = projectDAO.queryProject(criteria);
		mv.addObject("query", query);
		mv.addObject("projectList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}

	@RequestMapping("/detail/{projectId}")
	public ModelAndView detail(@PathVariable Long projectId, ViewRequest req) {
		ModelAndView mv = createModelView("project_detail", req);
		mv.addObject("viewname", PROJECT_LIST);
		try {
			Project project = projectDAO.get(projectId);
			mv.addObject("project", project);
			mv.addObject("success", project != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}
}