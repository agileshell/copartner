package com.insoul.ti.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.Admin;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.util.IpUtil;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.UserListRequest;
import com.insoul.ti.utils.Constants;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:23
 */
@Controller
@RequestMapping("/")
public class HomeController extends WebBase {

    private static final String DEFAULT_ADMIN_SALT = "68742f";

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = createModelView("login");
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        HttpSession session = request.getSession(true);
        session.invalidate();
        ModelAndView mv = createModelView("login");
        return mv;
    }

    private ModelAndView loginError(String errorMessage) {
        ModelAndView mv = createModelView("login");
        mv.addObject("success", false);
        mv.addObject("message", errorMessage);
        return mv;
    }

    @RequestMapping("/login_action")
    public ModelAndView login_action() {
        String loginName = request.getParameter("name");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return loginError("请输入用户名密码!!!");
        }
        Admin admin = null;
        try {
            admin = adminDAO.queryAdmin(loginName);
            if (admin == null) {
                return loginError("用户不存在!!!");
            }
        } catch (Throwable e) {
            return loginError("用户不存在!!!");
        }
        if (StringUtils.equals(PasswordUtil.encodePassword(password, DEFAULT_ADMIN_SALT), admin.getPassword())) {
            try {
                admin.setLastLogin(new Date());
                admin.setLastIp(IpUtil.ip2Long(IpUtil.getIpAddr(request)));
                adminDAO.update(admin);
                HttpSession session = request.getSession();
                session.setAttribute(Constants.ADMIN_ONLINE, admin);
                return new ModelAndView("redirect:/home");
            } catch (Throwable e) {
                return loginError("登录异常!!!");
            }
        }
        return loginError("用户名或密码不对!!!");
    }

    @RequestMapping("/home")
    public ModelAndView home(@Valid UserListRequest request, BindingResult result) {
        ModelAndView mv = createModelView("home", request);
        PageQuery query = request.init().getQuery();
        UserCriteria criteria = new UserCriteria();
        criteria.setLimit(query.getPage_size() * 100);
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
        mv.addObject("startupRoles", getStartupRoleMap());
        return mv;
    }
}
