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

import com.insoul.copartner.dao.criteria.RoleCriteria;
import com.insoul.copartner.domain.StartupRole;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.RoleListRequest;
import com.insoul.ti.req.RoleRequest;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/role")
public class RoleController extends WebBase {

	@RequestMapping("/list")
	public ModelAndView list(@Valid RoleListRequest request, BindingResult result) {
		ModelAndView mv = createModelView("role_list", request);
		PageQuery query = request.init().getQuery();
		RoleCriteria criteria = new RoleCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setId(request.getId());
		criteria.setName(request.getName());
		criteria.setListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		List<StartupRole> list = startupRoleDAO.query(criteria);
		mv.addObject("query", query);
		mv.addObject("roleList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		return createModelView("role_add", req);
	}

	@RequestMapping("/edit/{roleId}")
	public ModelAndView edit(@PathVariable Long roleId, ViewRequest req) {
		ModelAndView mv = createModelView("role_edit", req);
		StartupRole role = startupRoleDAO.get(roleId);
		mv.addObject("role", role);
		return mv;
	}

	@RequestMapping("/update/{roleId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long roleId, @Valid RoleRequest request, BindingResult result) {
		StartupRole role = startupRoleDAO.get(roleId);
		role.setUpdated(new Date());
		role.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		role.setName(request.getName());
		startupRoleDAO.update(role);
		return new ModelAndView("redirect:/role/list?id=" + roleId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid RoleRequest request, BindingResult result) {
		StartupRole role = new StartupRole();
		Date time = new Date();
		role.setCreated(time);
		role.setUpdated(time);
		role.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
		role.setName(request.getName());
		startupRoleDAO.save(role);
		return new ModelAndView("redirect:/role/list?id=" + role.getId());
	}
}