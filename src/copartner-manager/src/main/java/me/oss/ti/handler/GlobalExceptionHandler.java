package me.oss.ti.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import me.oss.ti.WebBase;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月26日 下午4:01:37
 */
public class GlobalExceptionHandler extends WebBase implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("Handler : " + handler + " Error.", ex);
		ModelAndView mv = new ModelAndView("");
		mv.addObject("status", 500);
		mv.addObject("message", ex.getLocalizedMessage());
		return mv;
	}
}