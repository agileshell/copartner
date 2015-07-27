package com.insoul.ti.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.User;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.UserListRequest;
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

	@RequestMapping("/list")
	public ModelAndView list(@Valid UserListRequest request, BindingResult result) {
		ModelAndView mv = createModelView("project_list", request);
		PageQuery query = request.init().getQuery();
		UserCriteria criteria = new UserCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setId(request.getId());
		criteria.setEmail(request.getEmail());
		criteria.setMobile(request.getMobile());
		criteria.setName(request.getName());
		List<User> list = userDAO.query(criteria);
		mv.addObject("query", query);
		mv.addObject("userList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}

	@RequestMapping("/detail/{projectId}")
	public ModelAndView detail(@PathVariable Long userId, ViewRequest req) {
		ModelAndView mv = createModelView("project_detail", req);
		mv.addObject("viewname", "project_list");
		try {
			User user = userDAO.get(userId);
			mv.addObject("user", user);
			mv.addObject("success", user != null);
			Long startupStatusId = user.getStartupStatusId();
			if (startupStatusId != null && startupStatusId > 0L) {
				mv.addObject("startupStatus", startupStatusDAO.get(startupStatusId).getName());
			}
			Long startupRoleId = user.getStartupRoleId();
			if (startupRoleId != null && startupRoleId > 0L) {
				mv.addObject("startupRole", startupRoleDAO.get(startupRoleId).getName());
			}
			StringBuilder sb = new StringBuilder();
			String[] domainIds = StringUtils.split(user.getDomains(), ",");
			if (domainIds != null && domainIds.length > 0) {
				for (String string : domainIds) {
					Long id = NumberUtils.toLong(string, 0L);
					if (id <= 0L) {
						continue;
					}
					IndustryDomain dom = industryDomainDAO.get(id);
					if (dom != null) {
						sb.append(dom.getName()).append(",");
					}
				}
			}
			mv.addObject("domains", sb.toString());
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}
}