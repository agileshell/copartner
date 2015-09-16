package com.insoul.ti;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.constant.GlobalProperties;
import com.insoul.copartner.dao.AdminDAO;
import com.insoul.copartner.dao.IAnswerDao;
import com.insoul.copartner.dao.IContentDao;
import com.insoul.copartner.dao.IDemandCommentsDao;
import com.insoul.copartner.dao.IDemandDao;
import com.insoul.copartner.dao.IFeedbackDao;
import com.insoul.copartner.dao.IFinancingDao;
import com.insoul.copartner.dao.IFinancingPhaseDao;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.INewsDao;
import com.insoul.copartner.dao.IProjectCommentsDao;
import com.insoul.copartner.dao.IProjectDao;
import com.insoul.copartner.dao.IProjectPhaseDao;
import com.insoul.copartner.dao.IQuestionCategoryDao;
import com.insoul.copartner.dao.IQuestionDao;
import com.insoul.copartner.dao.IStartupRoleDao;
import com.insoul.copartner.dao.IStartupStatusDao;
import com.insoul.copartner.dao.ISystemSettingDao;
import com.insoul.copartner.dao.ITeamSizeDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.domain.Admin;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.utils.Constants;
import com.insoul.ti.utils.Utils;

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
	
	@Resource
	protected IProjectDao projectDAO;
	
	@Resource
	protected AdminDAO adminDAO;
	
	@Resource
	protected ITeamSizeDao teamSizeDAO;
	
	@Resource
	protected IProjectCommentsDao projectCommentsDao;
	
	@Resource
	protected IDemandCommentsDao demandCommentsDao;

	@Resource
	protected IFinancingPhaseDao financingPhaseDAO;

	@Resource
	protected IDemandDao demandDAO;

	@Resource
	protected IFinancingDao financingDAO;

    @Resource
	protected IQuestionDao questionDAO;

    @Resource
    protected IQuestionCategoryDao questionCategoryDAO;

    @Resource
    protected IAnswerDao answerDAO;

	@Autowired
	@Qualifier("multipartResolver")
	protected CommonsMultipartResolver multipartResolver;

	private ServletContext servletContext;
	
    @Autowired
    protected HttpServletRequest request;
	
    @Autowired
    protected HttpServletResponse response;
    
    protected static final String COMMONS_RESOURCES_MANAGER_VIEW_NAME = "resources_manager";
    
	protected ModelAndView createModelView(String viewName) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("cdn", "/assets/");
        mv.addObject("cdnDomain", GlobalProperties.CDN_DOMAIN);
		mv.addObject("viewname", viewName);
		HttpSession session = request.getSession();
		mv.addObject(Constants.ADMIN_ONLINE, session.getAttribute(Constants.ADMIN_ONLINE));
		return mv;
	}

	protected ModelAndView createModelView(String viewName, ViewRequest req) {
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("cdn", "/assets/");
        mv.addObject("cdnDomain", GlobalProperties.CDN_DOMAIN);
		mv.addObject("utils", new Utils());
		mv.addObject("viewname", StringUtils.defaultIfBlank(req.getV(), viewName));
		HttpSession session = request.getSession();
		mv.addObject(Constants.ADMIN_ONLINE, session.getAttribute(Constants.ADMIN_ONLINE));
		return mv;
	}
	
	protected void returnJson(JSONObject json) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json; charset=UTF-8");
			out = response.getWriter();
			out.write(json.toString());
			out.flush();
		} catch (IOException e) {
			log.error("Return JSON Error.", e);
		} finally {
			if(out != null) {
				out.close();
				out = null;
			}
		}
	}
	
	protected void returnJson(boolean success, String code, String message) {
		try {
			JSONObject json = new JSONObject();
			json.accumulate("success", success);
			json.accumulate("code", code);
			json.accumulate("message", message);
			returnJson(json);
		} catch (Throwable e) {
			log.error("Return JSON Error.", e);
		}
	}
	
	protected long getAdminId() {
		HttpSession session = request.getSession();
		Object admin = session.getAttribute(Constants.ADMIN_ONLINE);
		if (admin == null) {
			return 0L;
		}
		return ((Admin) admin).getId();
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}