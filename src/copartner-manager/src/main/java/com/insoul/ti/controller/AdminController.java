package com.insoul.ti.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.domain.Admin;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.AdminRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;
import com.insoul.ti.utils.Constants;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:23
 */
@Controller
@RequestMapping("/admin")
@Permission("authc")
public class AdminController extends WebBase {
    
    private static final String ADMIN_EDIT = "admin_edit";
    private static final String ADMIN_ADD = "admin_add";
    private static final String ADMIN_LIST = "admin_list";

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView mv = createModelView(ADMIN_LIST);
        if (id != null && id > 0L) {
            try {
                Admin admin = adminDAO.get(id);
                if (admin != null) {
                    mv.addObject("adminList", Arrays.asList(admin));
                    mv.addObject("success", true);
                    return mv;
                }
            } catch (Exception e) {}
        }
        List<Admin> list = adminDAO.findAll();
        mv.addObject("adminList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add(ViewRequest req) {
        ModelAndView mv = createModelView(ADMIN_ADD, req);
        mv.addObject("viewname", ADMIN_LIST);
        return mv;
    }

    @RequestMapping("/edit/{adminId}")
    public ModelAndView edit(@PathVariable Long adminId, ViewRequest req) {
        ModelAndView mv = createModelView(ADMIN_EDIT, req);
        Admin admin = adminDAO.get(adminId);
        mv.addObject("admin", admin);
        mv.addObject("viewname", ADMIN_LIST);
        return mv;
    }

    @RequestMapping("/update/{adminId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView update(@PathVariable Long adminId, @Valid AdminRequest request, BindingResult result) {
        Admin admin = adminDAO.get(adminId);
        admin.setUpdated(new Date());
        admin.setLoginName(request.getLoginName());
        admin.setName(request.getName());
        admin.setPassword(PasswordUtil.encodePassword(request.getPassword(), Constants.DEFAULT_ADMIN_PASSWORD_SALT));
        admin.setPermission(request.getPermission());
        admin.setStatus(request.getStatus());
        adminDAO.update(admin);
        return new ModelAndView("redirect:/admin/list?id=" + adminId);
    }

    @RequestMapping("/save")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView save(@Valid AdminRequest request, BindingResult result) {
        Admin admin = new Admin();
        Date date = new Date();
        admin.setCreated(date);
        admin.setUpdated(date);
        admin.setLoginName(request.getLoginName());
        admin.setName(request.getName());
        admin.setPassword(PasswordUtil.encodePassword(request.getPassword(), Constants.DEFAULT_ADMIN_PASSWORD_SALT));
        admin.setPermission(request.getPermission());
        admin.setStatus(request.getStatus());
        adminDAO.save(admin);
        return new ModelAndView("redirect:/admin/list?id=" + admin.getId());
    }
}