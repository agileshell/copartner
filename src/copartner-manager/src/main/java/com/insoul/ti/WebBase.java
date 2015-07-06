package com.insoul.ti;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.IContentDao;
import com.insoul.copartner.dao.IFeedbackDao;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.INewsDao;
import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.IStartupStatusDao;
import com.insoul.copartner.dao.ISystemSettingDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:38:04
 */
public class WebBase implements ServletContextAware {

	protected Log log = LogFactory.getLog(getClass());
	
	@Resource
	protected IContentDao contentDAO;
	
	@Resource
	protected INewsDao newsDAO;
	
	@Resource
	protected ISystemSettingDao systemSettingDAO;
	
	@Resource
	protected IUserDao userDAO;
	
	@Resource
	protected IIndustryDomainDao industryDomainDAO;
	
	@Resource
	protected IStartupRoleDao startupRoleDAO;
	
	@Resource
	protected IStartupStatusDao startupStatusDAO;
	
	@Resource
	protected IFeedbackDao feedbackDAO;
	
	@Resource
	protected IProjectPhaseDao projectPhaseDAO;

	@Autowired
	@Qualifier("multipartResolver")
	protected CommonsMultipartResolver multipartResolver;

	private ServletContext servletContext;

	protected ModelAndView createModelView(String viewName) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("cdn", "/assets/");
		mv.addObject("viewname", viewName);
		return mv;
	}

	protected ModelAndView createModelView(String viewName, ViewRequest req) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("cdn", "/assets/");
		mv.addObject("viewname", StringUtils.defaultIfBlank(req.getV(), viewName));
		return mv;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}