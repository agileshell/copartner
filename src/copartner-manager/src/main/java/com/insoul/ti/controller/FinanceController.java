package com.insoul.ti.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.FinancingCriteria;
import com.insoul.copartner.domain.Financing;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.TeamSize;
import com.insoul.copartner.domain.User;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ProjectListRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.vo.FinanceVO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/finance")
public class FinanceController extends WebBase {

    private static final String FINANCE_DETAIL = "financing_detail";
	private static final String FINANCE_LIST = "financing_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid ProjectListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(FINANCE_LIST, request);
        PageQuery query = request.init().getQuery();
        FinancingCriteria criteria = new FinancingCriteria();
        criteria.setLimit(query.getPage_size());
        criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
        criteria.setStatus(new String[] { request.getStatus() });
        criteria.setProjectName(request.getName());
        criteria.setUserId(request.getId());
        List<Financing> list = financingDAO.queryFinancing(criteria);
        mv.addObject("query", query);
        mv.addObject("financingList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        return mv;
    }

    @RequestMapping("/update_status/{financingId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView updateStatus(@PathVariable Long financingId,
            @RequestParam(value = "status", required = true) String status) {
        try {
        	Financing financing = financingDAO.get(financingId);
        	financing.setStatus(status);
        	financingDAO.update(financing);
            returnJson(true, "200", "修改成功!!");
        } catch (Exception e) {
            returnJson(true, "500", "修改失败!!");
        }
        return null;
    }

    @RequestMapping("/detail/{financingId}")
    public ModelAndView detail(@PathVariable Long financingId, ViewRequest req) {
        ModelAndView mv = createModelView(FINANCE_DETAIL, req);
        mv.addObject("viewname", FINANCE_LIST);
        try {
        	FinanceVO finance = getFinancing(financingId);
            mv.addObject("finance", finance);
            mv.addObject("success", finance != null);
        } catch (Exception e) {
            mv.addObject("success", false);
        }
        return mv;
    }

    private FinanceVO getFinancing(Long financingId) {
    	Financing financing = financingDAO.get(financingId);
        if (financing == null) {
        	return null;
        }
        FinanceVO vo = new FinanceVO();
        vo.setAdvantage(financing.getAdvantage());
        vo.setContact(financing.getContact());
        vo.setContactPerson(financing.getContactPerson());
        vo.setContent(financing.getContent());
        vo.setCreated(financing.getCreated());
        vo.setFullLocation(financing.getFullLocation());
        vo.setId(financing.getId());
        vo.setName(financing.getProjectName());
        vo.setStatus(financing.getStatus());
        vo.setUpdated(financing.getUpdated());
        vo.setUserId(financing.getUserId());
        vo.setHasBusinessRegistered(financing.getHasBusinessRegistered());
        IndustryDomain i = industryDomainDAO.get(financing.getIndustryDomainId());
        if (i != null)
            vo.setIndustryDomainName(i.getName());
        TeamSize tz = teamSizeDAO.get(financing.getTeamSizeId());
        if (tz != null)
            vo.setTeamSizeName(tz.getName());
        User u = userDAO.get(financing.getUserId());
        if (u != null)
            vo.setUserName(u.getName());
        return vo;
    }
}