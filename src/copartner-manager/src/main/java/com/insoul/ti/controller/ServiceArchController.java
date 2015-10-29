package com.insoul.ti.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.domain.ServiceArch;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.FinancePhaseListRequest;
import com.insoul.ti.req.ServiceArchRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/srvarch")
@Permission("authc")
public class ServiceArchController extends WebBase {

    private static final String SRVARCH_EDIT = "srvarch_edit";
    private static final String SRVARCH_ADD = "srvarch_add";
    private static final String SRVARCH_LIST = "srvarch_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid FinancePhaseListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(SRVARCH_LIST, request);
        List<ServiceArch> list = serviceArchDAO.findAll();
        mv.addObject("srvarchList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add(ViewRequest req) {
        ModelAndView mv = createModelView(SRVARCH_ADD, req);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/edit/{srvarchId}")
    public ModelAndView edit(@PathVariable Long srvarchId, ViewRequest req) {
        ModelAndView mv = createModelView(SRVARCH_EDIT, req);
        ServiceArch serviceArch = serviceArchDAO.get(srvarchId);
        mv.addObject("srvarch", serviceArch);
        mv.addObject("viewname", COMMONS_RESOURCES_MANAGER_VIEW_NAME);
        return mv;
    }

    @RequestMapping("/update/{srvarchId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView update(@PathVariable Long srvarchId, @Valid ServiceArchRequest request, BindingResult result) {
        ServiceArch serviceArch = serviceArchDAO.get(srvarchId);
        MultipartFile image = request.getIcon();
        String path = StringUtils.EMPTY;
        if (image != null) {
            String fileType = FileUtil.getFileType(image.getOriginalFilename());
            if (StringUtils.isNotBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    path = CDNUtil.uploadFile(image.getInputStream(), fileName);
                    if (StringUtils.isNotBlank(path))
                        serviceArch.setIcon(path);
                } catch (Exception e) {
                    log.error("UploadFile Error.", e);
                }
            }
        }
        serviceArch.setUpdated(new Date());
        serviceArch.setDescription(request.getDescription());
        serviceArch.setName(request.getName());
        serviceArch.setStatus(request.getStatus());
        serviceArchDAO.update(serviceArch);
        return new ModelAndView("redirect:/srvarch/list");
    }

    @RequestMapping("/save")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView save(@Valid ServiceArchRequest request, BindingResult result) {
        ServiceArch serviceArch = new ServiceArch();
        MultipartFile image = request.getIcon();
        String path = StringUtils.EMPTY;
        if (image != null) {
            String fileType = FileUtil.getFileType(image.getOriginalFilename());
            if (StringUtils.isNotBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    path = CDNUtil.uploadFile(image.getInputStream(), fileName);
                    if (StringUtils.isNotBlank(path))
                        serviceArch.setIcon(path);
                } catch (Exception e) {
                    log.error("UploadFile Error.", e);
                }
            }
        }
        Date time = new Date();
        serviceArch.setCreated(time);
        serviceArch.setUpdated(time);
        serviceArch.setDescription(request.getDescription());
        serviceArch.setName(request.getName());
        serviceArch.setStatus(request.getStatus());
        serviceArchDAO.save(serviceArch);
        return new ModelAndView("redirect:/srvarch/list");
    }
}
