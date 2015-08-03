package com.insoul.ti.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.domain.StartupRole;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.RoleListRequest;
import com.insoul.ti.req.RoleRequest;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/role")
public class RoleController extends WebBase {

    private static final String ROLE_EDIT = "role_edit";
    private static final String ROLE_ADD = "role_add";
    private static final String ROLE_LIST = "role_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid RoleListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(ROLE_LIST, request);
        List<StartupRole> list = startupRoleDAO.findAll();
        mv.addObject("roleList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add(ViewRequest req) {
        ModelAndView mv = createModelView(ROLE_ADD, req);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/edit/{roleId}")
    public ModelAndView edit(@PathVariable Long roleId, ViewRequest req) {
        ModelAndView mv = createModelView(ROLE_EDIT, req);
        StartupRole role = startupRoleDAO.get(roleId);
        mv.addObject("role", role);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/update/{roleId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView update(@PathVariable Long roleId, @Valid RoleRequest request, BindingResult result) {
        StartupRole role = startupRoleDAO.get(roleId);
        role.setUpdated(new Date());
        role.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
        role.setName(request.getName());
        startupRoleDAO.update(role);
        return new ModelAndView("redirect:/role/list");
    }

    @RequestMapping("/save")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView save(@Valid RoleRequest request, BindingResult result) {
        StartupRole role = new StartupRole();
        Date time = new Date();
        role.setCreated(time);
        role.setUpdated(time);
        role.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
        role.setName(request.getName());
        startupRoleDAO.save(role);
        return new ModelAndView("redirect:/role/list");
    }
}