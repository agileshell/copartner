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

import com.insoul.copartner.dao.criteria.ContentCriteria;
import com.insoul.copartner.domain.Admin;
import com.insoul.copartner.domain.Content;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.copartner.util.IpUtil;
import com.insoul.copartner.util.PasswordUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.AdminRequest;
import com.insoul.ti.req.ContentListRequest;
import com.insoul.ti.req.ContentRequest;
import com.insoul.ti.req.PageQuery;
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
    private static final String ADMIN_DETAIL = "admin_detail";
    private static final String ADMIN_LIST = "admin_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid ContentListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(ADMIN_LIST, request);
        PageQuery query = request.init().getQuery();
        ContentCriteria criteria = new ContentCriteria();
        criteria.setLimit(query.getPage_size());
        criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
        criteria.setType(request.getType());
        String[] status = null;
        if (StringUtils.isNotBlank(request.getStatus())) {
            status = new String[] { request.getStatus() };
        }
        criteria.setStatus(status);
        criteria.setId(request.getId());
        criteria.setTitle(request.getTitle());
        List<Content> list = contentDAO.queryContent(criteria);
        mv.addObject("query", query);
        mv.addObject("contentList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        return mv;
    }

    @RequestMapping("/detail/{contentId}")
    public ModelAndView detail(@PathVariable Long contentId, ViewRequest req) {
        ModelAndView mv = createModelView(ADMIN_DETAIL, req);
        mv.addObject("viewname", ADMIN_LIST);
        try {
            Content content = contentDAO.get(contentId);
            mv.addObject("content", content);
            mv.addObject("success", content != null);
        } catch (Exception e) {
            mv.addObject("success", false);
        }
        return mv;
    }

    @RequestMapping("/add")
    public ModelAndView add(ViewRequest req) {
        ModelAndView mv = createModelView(ADMIN_ADD, req);
        mv.addObject("viewname", ADMIN_LIST);
        return mv;
    }

    @RequestMapping("/edit/{contentId}")
    public ModelAndView edit(@PathVariable Long contentId, ViewRequest req) {
        ModelAndView mv = createModelView(ADMIN_EDIT, req);
        Content content = contentDAO.get(contentId);
        mv.addObject("content", content);
        mv.addObject("viewname", ADMIN_LIST);
        return mv;
    }

    @RequestMapping("/update/{contentId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView update(@PathVariable Long contentId, @Valid ContentRequest request, BindingResult result) {
        Content content = contentDAO.get(contentId);
        MultipartFile image = request.getCoverImg();
        if (image != null) {
            String fileType = FileUtil.getFileType(image.getOriginalFilename());
            if (StringUtils.isNoneBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
                    if (StringUtils.isNoneBlank(path)) content.setCoverImg(path);
                } catch (Exception e) {
                    log.error("UploadFile Error.", e);
                }
            }
        }
        content.setArticle(request.getArticle());
        content.setUpdated(new Date());
        content.setStatus(request.getStatus());
        content.setSynopsis(request.getSynopsis());
        content.setTitle(request.getTitle());
        content.setType(request.getType());
        contentDAO.update(content);
        return new ModelAndView("redirect:/content/detail/" + contentId);
    }

    @RequestMapping("/save")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView save(@Valid ContentRequest request, BindingResult result) {
        MultipartFile image = request.getCoverImg();
        String path = StringUtils.EMPTY;
        if (image != null) {
            String fileType = FileUtil.getFileType(image.getOriginalFilename());
            if (StringUtils.isNoneBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    path = CDNUtil.uploadFile(image.getInputStream(), fileName);
                } catch (Exception e) {
                    log.error("UploadFile Error.", e);
                }
            }
        }
        Content content = new Content();
        if (StringUtils.isNoneBlank(path)) content.setCoverImg(path);
        content.setAdminUserId(getAdminId());
        content.setArticle(request.getArticle());
        content.setClicks(0L);
        Date time = new Date();
        content.setCreated(time);
        content.setUpdated(time);
        content.setStatus(request.getStatus());
        content.setSynopsis(request.getSynopsis());
        content.setTitle(request.getTitle());
        content.setType(request.getType());
        contentDAO.save(content);
        return new ModelAndView("redirect:/content/detail/" + content.getId());
    }
    
    @RequestMapping("/update_action")
    public ModelAndView updateAdmin(AdminRequest request) {
        Admin admin = new Admin();
        Date date = new Date();
        admin.setCreated(date);
        admin.setUpdated(date);
        admin.setLastIp(IpUtil.ip2Long(IpUtil.getIpAddr(this.request)));
        admin.setLoginName(request.getLoginName());
        admin.setName(request.getName());
        admin.setPassword(PasswordUtil.encodePassword(request.getPassword(), Constants.DEFAULT_ADMIN_PASSWORD_SALT));
        admin.setPermission(request.getPermission());
        admin.setStatus(request.getStatus());
        adminDAO.save(admin);
        return new ModelAndView("redirect:/admin/detail/" + admin.getId());
    }
}