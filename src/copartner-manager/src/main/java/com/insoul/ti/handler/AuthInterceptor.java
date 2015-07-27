package com.insoul.ti.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.insoul.ti.utils.Constants;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:30
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect("/login");
			return false;
		}
		if (session.getAttribute(Constants.ADMIN_NAME) == null) {
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}