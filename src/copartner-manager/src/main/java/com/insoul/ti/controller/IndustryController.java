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

import com.insoul.copartner.dao.criteria.IndustryCriteria;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.IndustryListRequest;
import com.insoul.ti.req.IndustryRequest;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/industry")
public class IndustryController extends WebBase {

	private static final String INDUSTRY_EDIT = "industry_edit";
	private static final String INDUSTRY_ADD = "industry_add";
	private static final String INDUSTRY_LIST = "industry_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid IndustryListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(INDUSTRY_LIST, request);
		PageQuery query = request.init().getQuery();
		IndustryCriteria criteria = new IndustryCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setId(request.getId());
		criteria.setName(request.getName());
		criteria.setListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		List<IndustryDomain> list = industryDomainDAO.query(criteria);
		mv.addObject("query", query);
		mv.addObject("industryList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(INDUSTRY_ADD, req);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/edit/{industryId}")
	public ModelAndView edit(@PathVariable Long industryId, ViewRequest req) {
		ModelAndView mv = createModelView(INDUSTRY_EDIT, req);
		IndustryDomain industry = industryDomainDAO.get(industryId);
		mv.addObject("industry", industry);
		mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
		return mv;
	}

	@RequestMapping("/update/{industryId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long industryId, @Valid IndustryRequest request, BindingResult result) {
		IndustryDomain industry = industryDomainDAO.get(industryId);
		industry.setUpdated(new Date());
		industry.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		industry.setName(request.getName());
		industryDomainDAO.update(industry);
		return new ModelAndView("redirect:/industry/list?id=" + industryId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid IndustryRequest request, BindingResult result) {
		IndustryDomain industry = new IndustryDomain();
		Date time = new Date();
		industry.setCreated(time);
		industry.setUpdated(time);
		industry.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		industry.setName(request.getName());
		industryDomainDAO.save(industry);
		return new ModelAndView("redirect:/industry/list?id=" + industry.getId());
	}
}