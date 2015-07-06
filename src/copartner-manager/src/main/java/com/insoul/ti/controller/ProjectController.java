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

import com.insoul.copartner.dao.criteria.ProjectPhaseCriteria;
import com.insoul.copartner.domain.ProjectPhase;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ProjectPhaseListRequest;
import com.insoul.ti.req.ProjectPhaseRequest;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/pro")
public class ProjectController extends WebBase {

	@RequestMapping("/list")
	public ModelAndView list(@Valid ProjectPhaseListRequest request, BindingResult result) {
		ModelAndView mv = createModelView("pro_list", request);
		PageQuery query = request.init().getQuery();
		ProjectPhaseCriteria criteria = new ProjectPhaseCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setId(request.getId());
		criteria.setName(request.getName());
		criteria.setListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		List<ProjectPhase> list = projectPhaseDAO.query(criteria);
		mv.addObject("query", query);
		mv.addObject("phaseList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		return createModelView("pro_add", req);
	}

	@RequestMapping("/edit/{proId}")
	public ModelAndView edit(@PathVariable Long proId, ViewRequest req) {
		ModelAndView mv = createModelView("pro_edit", req);
		ProjectPhase phase = projectPhaseDAO.get(proId);
		mv.addObject("phase", phase);
		return mv;
	}

	@RequestMapping("/update/{proId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long proId, @Valid ProjectPhaseRequest request, BindingResult result) {
		ProjectPhase phase = projectPhaseDAO.get(proId);
		phase.setUpdated(new Date());
		phase.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		phase.setName(request.getName());
		projectPhaseDAO.update(phase);
		return new ModelAndView("redirect:/pro/list?id=" + proId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid ProjectPhaseRequest request, BindingResult result) {
		ProjectPhase phase = new ProjectPhase();
		Date time = new Date();
		phase.setCreated(time);
		phase.setUpdated(time);
		phase.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		phase.setName(request.getName());
		projectPhaseDAO.save(phase);
		return new ModelAndView("redirect:/pro/list?id=" + phase.getId());
	}
}