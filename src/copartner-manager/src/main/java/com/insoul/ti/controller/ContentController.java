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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.ContentCriteria;
import com.insoul.copartner.domain.Content;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.ContentListRequest;
import com.insoul.ti.req.ContentRequest;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/content")
@Permission("authc")
public class ContentController extends WebBase {

	private static final String CONTENT_EDIT = "content_edit";
	private static final String CONTENT_ADD = "content_add";
	private static final String CONTENT_DETAIL = "content_detail";
	private static final String CONTENT_LIST = "content_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid ContentListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(CONTENT_LIST, request);
		PageQuery query = request.init().getQuery();
		ContentCriteria criteria = new ContentCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		String[] status = null;
		if (StringUtils.isNotBlank(request.getStatus())) {
			status = new String[] { request.getStatus() };
		}
		criteria.setStatus(status);
		criteria.setId(request.getId());
		criteria.setTitle(request.getTitle());
		List<Content> list = contentDAO.queryContent(criteria);
		Long count = contentDAO.countContent(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("contentList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}
	
	@RequestMapping("/update_status/{contentId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView updateStatus(@PathVariable Long contentId, @RequestParam(value = "status", required = true) String status) {
		try {
			Content content = contentDAO.get(contentId);
			content.setStatus(status);
			contentDAO.update(content);
			returnJson(true, "200", "修改成功!!");
		} catch (Exception e) {
			returnJson(true, "500", "修改失败!!");
		}
		return null;
	}

	@RequestMapping("/detail/{contentId}")
	public ModelAndView detail(@PathVariable Long contentId, ViewRequest req) {
		ModelAndView mv = createModelView(CONTENT_DETAIL, req);
		mv.addObject("viewname", CONTENT_LIST);
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
		ModelAndView mv = createModelView(CONTENT_ADD, req);
		mv.addObject("viewname", CONTENT_LIST);
		return mv;
	}

	@RequestMapping("/edit/{contentId}")
	public ModelAndView edit(@PathVariable Long contentId, ViewRequest req) {
		ModelAndView mv = createModelView(CONTENT_EDIT, req);
		Content content = contentDAO.get(contentId);
		mv.addObject("content", content);
		mv.addObject("viewname", CONTENT_LIST);
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
		contentDAO.save(content);
		return new ModelAndView("redirect:/content/detail/" + content.getId());
	}
}