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

import com.insoul.copartner.domain.StartupStatus;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.StatusListRequest;
import com.insoul.ti.req.StatusRequest;
import com.insoul.ti.req.ViewRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/status")
public class StatusController extends WebBase {

    private static final String STATUS_EDIT = "status_edit";
    private static final String STATUS_ADD = "status_add";
    private static final String STATUS_LIST = "status_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid StatusListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(STATUS_LIST, request);
        List<StartupStatus> list = startupStatusDAO.findAll();
        mv.addObject("statusList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add(ViewRequest req) {
        ModelAndView mv = createModelView(STATUS_ADD, req);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/edit/{statusId}")
    public ModelAndView edit(@PathVariable Long statusId, ViewRequest req) {
        ModelAndView mv = createModelView(STATUS_EDIT, req);
        StartupStatus status = startupStatusDAO.get(statusId);
        mv.addObject("status", status);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/update/{statusId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView update(@PathVariable Long statusId, @Valid StatusRequest request, BindingResult result) {
        StartupStatus status = startupStatusDAO.get(statusId);
        status.setUpdated(new Date());
        status.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
        status.setName(request.getName());
        startupStatusDAO.update(status);
        return new ModelAndView("redirect:/status/list?id=" + statusId);
    }

    @RequestMapping("/save")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView save(@Valid StatusRequest request, BindingResult result) {
        StartupStatus status = new StartupStatus();
        Date time = new Date();
        status.setCreated(time);
        status.setUpdated(time);
        status.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
        status.setName(request.getName());
        startupStatusDAO.save(status);
        return new ModelAndView("redirect:/status/list?id=" + status.getId());
    }
}