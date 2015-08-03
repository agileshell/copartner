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

import com.insoul.copartner.domain.FinancingPhase;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.FinancePhaseListRequest;
import com.insoul.ti.req.FinancePhaseRequest;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/finaphase")
public class FinancePhaseController extends WebBase {

	private static final String PHASE_EDIT = "finaphase_edit";
	private static final String PHASE_ADD = "finaphase_add";
	private static final String PHASE_LIST = "finaphase_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid FinancePhaseListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(PHASE_LIST, request);
		List<FinancingPhase> list = financingPhaseDAO.findAll();
		mv.addObject("phaseList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(PHASE_ADD, req);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/edit/{finaphaseId}")
	public ModelAndView edit(@PathVariable Long finaphaseId, ViewRequest req) {
		ModelAndView mv = createModelView(PHASE_EDIT, req);
		FinancingPhase phase = financingPhaseDAO.get(finaphaseId);
		mv.addObject("phase", phase);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/update/{finaphaseId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long finaphaseId, @Valid FinancePhaseRequest request, BindingResult result) {
		FinancingPhase phase = financingPhaseDAO.get(finaphaseId);
		phase.setUpdated(new Date());
		phase.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		phase.setName(request.getName());
		financingPhaseDAO.update(phase);
		return new ModelAndView("redirect:/finaphase/list");
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid FinancePhaseRequest request, BindingResult result) {
		FinancingPhase phase = new FinancingPhase();
		Date time = new Date();
		phase.setCreated(time);
		phase.setUpdated(time);
		phase.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		phase.setName(request.getName());
		financingPhaseDAO.save(phase);
		return new ModelAndView("redirect:/finaphase/list");
	}
}