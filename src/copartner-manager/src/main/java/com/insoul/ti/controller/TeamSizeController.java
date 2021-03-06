package com.insoul.ti.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.domain.TeamSize;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.TeamSizeListRequest;
import com.insoul.ti.req.TeamSizeRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/team")
@Permission("authc")
public class TeamSizeController extends WebBase {

	private static final String TEAM_SIZE_EDIT = "team_edit";
	private static final String TEAM_SIZE_ADD = "team_add";
	private static final String TEAM_SIZE_LIST = "team_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid TeamSizeListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(TEAM_SIZE_LIST, request);
		List<TeamSize> list = teamSizeDAO.findAll();
		mv.addObject("teamSizeList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(TEAM_SIZE_ADD, req);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/edit/{teamId}")
	public ModelAndView edit(@PathVariable Long teamId, ViewRequest req) {
		ModelAndView mv = createModelView(TEAM_SIZE_EDIT, req);
		TeamSize teamSize = teamSizeDAO.get(teamId);
		mv.addObject("teamSize", teamSize);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/update/{teamId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long teamId, @Valid TeamSizeRequest request, BindingResult result) {
		TeamSize teamSize = teamSizeDAO.get(teamId);
		teamSize.setUpdated(new Date());
		teamSize.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		teamSize.setName(request.getName());
		teamSizeDAO.update(teamSize);
		return new ModelAndView("redirect:/team/list");
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid TeamSizeRequest request, BindingResult result) {
		TeamSize teamSize = new TeamSize();
		Date time = new Date();
		teamSize.setCreated(time);
		teamSize.setUpdated(time);
		teamSize.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		teamSize.setName(request.getName());
		teamSizeDAO.save(teamSize);
		return new ModelAndView("redirect:/team/list");
	}
}