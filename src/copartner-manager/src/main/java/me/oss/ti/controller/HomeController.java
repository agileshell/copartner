package me.oss.ti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import me.oss.ti.WebBase;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月26日 下午4:10:58
 */
@Controller
@RequestMapping("/")
public class HomeController extends WebBase {

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = createModelView("login");

		return mv;
	}
}