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
import com.insoul.copartner.domain.Content;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.ContentListRequest;
import com.insoul.ti.req.ContentRequest;
import com.insoul.ti.req.PageQuery;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/content")
public class ContentController extends WebBase {

	@RequestMapping("/list")
	public ModelAndView list(@Valid ContentListRequest request, BindingResult result) {
		ModelAndView mv = createModelView("content_list");
		PageQuery query = request.init().getQuery();
		ContentCriteria criteria = new ContentCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setType(request.getType());
		List<Content> list = contentDAO.queryContent(criteria);
		mv.addObject("query", query);
		mv.addObject("contentList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		return mv;
	}

	@RequestMapping("/detail/{contentId}")
	public ModelAndView detail(@PathVariable Long contentId) {
		ModelAndView mv = createModelView("content_detail");
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
	public ModelAndView add() {
		return createModelView("content_add");
	}

	@RequestMapping("/edit/{contentId}")
	public ModelAndView edit(@PathVariable Long contentId) {
		ModelAndView mv = createModelView("content_edit");
		Content content = contentDAO.get(contentId);
		mv.addObject("content", content);
		return mv;
	}

	@RequestMapping("/update/{contentId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long contentId, @Valid ContentRequest request, BindingResult result) {
		Content content = contentDAO.get(contentId);
		MultipartFile image = request.getCoverImg();
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
			try {
				String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
				content.setCoverImg(path);
			} catch (Exception e) {
				log.error("UploadFile Error.", e);
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
			String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
			try {
				path = CDNUtil.uploadFile(image.getInputStream(), fileName);
			} catch (Exception e) {
				log.error("UploadFile Error.", e);
			}
		}
		Content content = new Content();
		content.setCoverImg(path);
		content.setAdminUserId(0L);
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
}