package com.insoul.ti.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.ti.WebBase;
import com.insoul.ti.req.UserListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/commons")
public class CommonsController extends WebBase {

	@RequestMapping("/manager")
	public ModelAndView manager(@Valid UserListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(MANAGER_VIEW_NAME, request);
		return mv;
	}
}