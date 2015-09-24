package com.insoul.ti.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.UserCriteria;
import com.insoul.copartner.domain.Admin;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.util.IpUtil;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.UserListRequest;
import com.insoul.ti.shiro.Permission;
import com.insoul.ti.utils.Constants;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:23
 */
@Controller
public class HomeController extends WebBase {
    
    @RequestMapping("/unauthorized")
    @Permission("anon")
    private ModelAndView unauthorized() {
        ModelAndView mv = createModelView("error");
        mv.addObject("status", 100);
        mv.addObject("message", "授权认证失败!");
        return mv;
    }

    @RequestMapping("/login")
    @Permission("anon")
    public ModelAndView login() {
        ModelAndView mv = createModelView("login");
        return mv;
    }

    @RequestMapping("/logout")
    @Permission("anon")
    public ModelAndView logout() {
        Subject subjects = SecurityUtils.getSubject();
        if (subjects != null) {
            subjects.logout();
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/login_action")
    @Permission("anon")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView login_action(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return loginError("请输入用户名密码!!!");
        }
        Admin admin = null;
        try {
            admin = adminDAO.queryAdmin(name);
            if (admin == null) {
                return loginError("用户不存在!!!");
            }
            if (admin.getStatus() <= 0) {
                return loginError("用户已经被锁定!!!");
            }
            if (!admin.isPermissionLogin()) {
                return loginError("无权限!!!");
            }
        } catch (Throwable e) {
            return loginError("用户不存在!!!");
        }
        if (StringUtils.equals(PasswordUtil.encodePassword(password, Constants.DEFAULT_ADMIN_PASSWORD_SALT), admin.getPassword())) {
            try {
                admin.setLastLogin(new Date());
                admin.setLastIp(IpUtil.ip2Long(IpUtil.getIpAddr(request)));
                adminDAO.update(admin);
                UsernamePasswordToken token = new UsernamePasswordToken(name, password);
                token.setRememberMe(true);
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                return new ModelAndView("redirect:/home");
            } catch (Throwable e) {
                return loginError("登录异常!!!");
            }
        }
        return loginError("用户名或密码不对!!!");
    }

    @RequestMapping("/home")
    @Permission("authc")
    public ModelAndView home(@Valid UserListRequest request, BindingResult result) {
        ModelAndView mv = createModelView("home_2_0", request);
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

    private ModelAndView loginError(String errorMessage) {
        ModelAndView mv = createModelView("login");
        mv.addObject("success", false);
        mv.addObject("message", errorMessage);
        return mv;
    }
}
