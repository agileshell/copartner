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

import com.insoul.copartner.domain.ProjectPhase;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.ProjectPhaseListRequest;
import com.insoul.ti.req.ProjectPhaseRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/phase")
@Permission("authc")
public class ProjectPhaseController extends WebBase {

    private static final String PHASE_EDIT = "phase_edit";
    private static final String PHASE_ADD = "phase_add";
    private static final String PHASE_LIST = "phase_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid ProjectPhaseListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(PHASE_LIST, request);
        List<ProjectPhase> list = projectPhaseDAO.findAll();
        mv.addObject("phaseList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add(ViewRequest req) {
        ModelAndView mv = createModelView(PHASE_ADD, req);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/edit/{proId}")
    public ModelAndView edit(@PathVariable Long proId, ViewRequest req) {
        ModelAndView mv = createModelView(PHASE_EDIT, req);
        ProjectPhase phase = projectPhaseDAO.get(proId);
        mv.addObject("phase", phase);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/update/{proId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView update(@PathVariable Long proId, @Valid ProjectPhaseRequest request, BindingResult result) {
        ProjectPhase phase = projectPhaseDAO.get(proId);
        phase.setUpdated(new Date());
        phase.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
        phase.setName(request.getName());
        projectPhaseDAO.update(phase);
        return new ModelAndView("redirect:/phase/list");
    }

    @RequestMapping("/save")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView save(@Valid ProjectPhaseRequest request, BindingResult result) {
        ProjectPhase phase = new ProjectPhase();
        Date time = new Date();
        phase.setCreated(time);
        phase.setUpdated(time);
        phase.setIsListed(StringUtils.equals("1", String.valueOf(request.getListed())));
        phase.setName(request.getName());
        projectPhaseDAO.save(phase);
        return new ModelAndView("redirect:/phase/list");
    }
}