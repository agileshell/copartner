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

import com.insoul.copartner.dao.criteria.PioneerParkCriteria;
import com.insoul.copartner.domain.PioneerPark;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.PioneerParkListRequest;
import com.insoul.ti.req.PioneerParkRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/pioneerpark")
@Permission("authc")
public class PioneerParkController extends WebBase {

	private static final String PIONEERPARK_EDIT = "pioneerpark_edit";
	private static final String PIONEERPARK_ADD = "pioneerpark_add";
	private static final String PIONEERPARK_DETAIL = "pioneerpark_detail";
	private static final String PIONEERPARK_LIST = "pioneerpark_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid PioneerParkListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(PIONEERPARK_LIST, request);
		PageQuery query = request.init().getQuery();
		PioneerParkCriteria criteria = new PioneerParkCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setName(request.getName());
        List<PioneerPark> list = pioneerParkDAO.queryPioneerPark(criteria);
        Long count = pioneerParkDAO.countPioneerPark(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("pioneerParkList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}

	@RequestMapping("/detail/{pioneerParkId}")
	public ModelAndView detail(@PathVariable Long pioneerParkId, ViewRequest req) {
		ModelAndView mv = createModelView(PIONEERPARK_DETAIL, req);
		mv.addObject("viewname", PIONEERPARK_LIST);
		try {
		    PioneerPark pioneerPark = pioneerParkDAO.get(pioneerParkId);
            mv.addObject("pioneerPark", pioneerPark);
            mv.addObject("success", pioneerPark != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(PIONEERPARK_ADD, req);
		mv.addObject("viewname", PIONEERPARK_LIST);
		return mv;
	}

	@RequestMapping("/edit/{pioneerParkId}")
	public ModelAndView edit(@PathVariable Long pioneerParkId, ViewRequest req) {
		ModelAndView mv = createModelView(PIONEERPARK_EDIT, req);
		PioneerPark pioneerPark = pioneerParkDAO.get(pioneerParkId);
        mv.addObject("pioneerPark", pioneerPark);
		mv.addObject("viewname", PIONEERPARK_LIST);
		return mv;
	}

	@RequestMapping("/update/{pioneerParkId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long pioneerParkId, @Valid PioneerParkRequest request, BindingResult result) {
	    PioneerPark pioneerPark = pioneerParkDAO.get(pioneerParkId);
	    pioneerPark.setAddress(request.buildAddress());
	    pioneerPark.setAddressDetail(StringUtils.defaultString(request.getAddressDetail()));
	    pioneerPark.setArea(StringUtils.defaultString(request.getArea()));
	    pioneerPark.setCity(StringUtils.defaultString(request.getCity()));
	    pioneerPark.setContent(StringUtils.defaultString(request.getContent()));
	    pioneerPark.setLatitude(request.getLatitude());
	    pioneerPark.setLongitude(request.getLongitude());
	    pioneerPark.setName(request.getName());
	    pioneerPark.setProvince(request.getProvince());
	    pioneerPark.setUpdated(new Date());
	    pioneerParkDAO.update(pioneerPark);
		return new ModelAndView("redirect:/pioneerpark/detail/" + pioneerParkId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid PioneerParkRequest request, BindingResult result) {
	    PioneerPark pioneerPark = new PioneerPark();
        pioneerPark.setAddress(request.buildAddress());
        pioneerPark.setAddressDetail(StringUtils.defaultString(request.getAddressDetail()));
        pioneerPark.setArea(StringUtils.defaultString(request.getArea()));
        pioneerPark.setCity(StringUtils.defaultString(request.getCity()));
        pioneerPark.setContent(StringUtils.defaultString(request.getContent()));
        pioneerPark.setLatitude(request.getLatitude());
        pioneerPark.setLongitude(request.getLongitude());
        pioneerPark.setName(request.getName());
        pioneerPark.setProvince(request.getProvince());
        Date date = new Date();
        pioneerPark.setUpdated(date);
        pioneerPark.setCreated(date);
        pioneerParkDAO.save(pioneerPark);
		return new ModelAndView("redirect:/pioneerpark/detail/" + pioneerPark.getId());
	}
}