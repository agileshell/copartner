package com.insoul.ti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.ti.WebBase;

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
	public ModelAndView home() {
		return new ModelAndView("redirect:/user/list?limit=10&v=h");
	}
}