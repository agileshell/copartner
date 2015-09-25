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

import com.insoul.copartner.dao.criteria.DemandCriteria;
import com.insoul.copartner.domain.Demand;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.TeamSize;
import com.insoul.copartner.domain.User;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ProjectListRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;
import com.insoul.ti.vo.DemandVO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/demand")
@Permission("authc")
public class DemandController extends WebBase {

    private static final String DEMAND_DETAIL = "demand_detail";
	private static final String DEMAND_LIST = "demand_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid ProjectListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(DEMAND_LIST, request);
        PageQuery query = request.init().getQuery();
        DemandCriteria criteria = new DemandCriteria();
        criteria.setLimit(query.getPage_size());
        criteria.setOffset(query.getIndex());
        criteria.setStatus(new String[] { request.getStatus() });
        criteria.setProjectName(request.getName());
        criteria.setUserId(request.getId());
        List<Demand> list = demandDAO.queryDemand(criteria);
        Long count = demandDAO.countDemand(criteria);
        query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
        mv.addObject("query", query);
        mv.addObject("demandList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        return mv;
    }

    @RequestMapping("/update_status/{demandId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView updateStatus(@PathVariable Long demandId,
            @RequestParam(value = "status", required = true) String status) {
        try {
        	Demand demand = demandDAO.get(demandId);
        	demand.setStatus(status);
            demandDAO.update(demand);
            returnJson(true, "200", "修改成功!!");
        } catch (Exception e) {
            returnJson(true, "500", "修改失败!!");
        }
        return null;
    }

    @RequestMapping("/detail/{demandId}")
    public ModelAndView detail(@PathVariable Long demandId, ViewRequest req) {
        ModelAndView mv = createModelView(DEMAND_DETAIL, req);
        mv.addObject("viewname", DEMAND_LIST);
        try {
        	DemandVO demand = getDemand(demandId);
            mv.addObject("demand", demand);
            mv.addObject("success", demand != null);
        } catch (Exception e) {
            mv.addObject("success", false);
        }
        return mv;
    }

    private DemandVO getDemand(Long demandId) {
        Demand demand = demandDAO.get(demandId);
        if (demand == null) {
        	return null;
        }
    	DemandVO vo = new DemandVO();
        vo.setAdvantage(demand.getAdvantage());
        vo.setCommentCount(demand.getCommentCount());
        vo.setContact(demand.getContact());
        vo.setContactPerson(demand.getContactPerson());
        vo.setContent(demand.getContent());
        vo.setCreated(demand.getCreated());
        vo.setFullLocation(demand.getFullLocation());
        vo.setId(demand.getId());
        vo.setLikeCount(demand.getLikeCount());
        vo.setName(demand.getProjectName());
        vo.setStatus(demand.getStatus());
        vo.setUpdated(demand.getUpdated());
        vo.setUserId(demand.getUserId());
        vo.setHasBusinessRegistered(demand.getHasBusinessRegistered());
        IndustryDomain i = industryDomainDAO.get(demand.getIndustryDomainId());
        if (i != null)
            vo.setIndustryDomainName(i.getName());
        TeamSize tz = teamSizeDAO.get(demand.getTeamSizeId());
        if (tz != null)
            vo.setTeamSizeName(tz.getName());
        User u = userDAO.get(demand.getUserId());
        if (u != null)
            vo.setUserName(u.getName());
        return vo;
    }
}