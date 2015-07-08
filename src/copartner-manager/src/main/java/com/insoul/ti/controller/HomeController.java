package com.insoul.ti.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.User;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.UserListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:23
 */
@Controller
@RequestMapping("/")
public class HomeController extends WebBase {

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = createModelView("login");

		return mv;
	}

	@RequestMapping("/home")
	public ModelAndView home(@Valid UserListRequest request, BindingResult result) {
		ModelAndView mv = createModelView("home", request);
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
		mv.addObject("user_count", userDAO.count());
		mv.addObject("project_count", projectDAO.count());
		mv.addObject("content_count", contentDAO.count());
		return mv;
	}
}