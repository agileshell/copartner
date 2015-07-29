package com.insoul.ti.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.ProjectCriteria;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.Project;
import com.insoul.copartner.domain.ProjectPhase;
import com.insoul.copartner.domain.TeamSize;
import com.insoul.copartner.domain.User;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ProjectListRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.vo.ProjectVO;

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
	
	@RequestMapping("/update_status/{projectId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void updateStatus(@PathVariable Long projectId, String status) {
		try {
			Project p = projectDAO.get(projectId);
			p.setStatus(status);
			projectDAO.update(p);
			returnJson(true, "200", "修改成功!!");
		} catch (Exception e) {
			returnJson(true, "500", "修改失败!!");
		}
	}

	@RequestMapping("/detail/{projectId}")
	public ModelAndView detail(@PathVariable Long projectId, ViewRequest req) {
		ModelAndView mv = createModelView("project_detail", req);
		mv.addObject("viewname", PROJECT_LIST);
		try {
			ProjectVO project = getProject(projectId);
			mv.addObject("project", project);
			mv.addObject("success", project != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}
	
	private ProjectVO getProject(Long projectId) {
		ProjectVO vo = new ProjectVO();
		Project p = projectDAO.get(projectId);
		vo.setAdvantage(p.getAdvantage());
		vo.setCommentCount(p.getCommentCount());
		vo.setContact(p.getContact());
		vo.setContactPerson(p.getContactPerson());
		vo.setContent(p.getContent());
		vo.setCreated(p.getCreated());
		vo.setFullLocation(p.getFullLocation());
		vo.setId(p.getId());
		vo.setLikeCount(p.getLikeCount());
		vo.setLogo(p.getLogo());
		vo.setName(p.getName());
		vo.setStatus(p.getStatus());
		vo.setUpdated(p.getUpdated());
		vo.setUserId(p.getUserId());
		IndustryDomain i = industryDomainDAO.get(p.getIndustryDomainId());
		if (i != null) vo.setIndustryDomainName(i.getName());
		ProjectPhase pp = projectPhaseDAO.get(p.getProjectPhaseId());
		if (pp != null) vo.setProjectPhaseName(pp.getName());
		TeamSize tz = teamSizeDAO.get(p.getTeamSizeId());
		if (tz != null) vo.setTeamSizeName(tz.getName());
		User u = userDAO.get(p.getUserId());
		if (u != null) vo.setUserName(u.getName());
		return vo;
	}
}