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

import com.insoul.copartner.dao.criteria.NewsCriteria;
import com.insoul.copartner.domain.News;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.NewsListRequest;
import com.insoul.ti.req.NewsRequest;
import com.insoul.ti.req.PageQuery;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/news")
public class NewsController extends WebBase {

	@RequestMapping("/list")
	public ModelAndView list(@Valid NewsListRequest request, BindingResult result) {
		ModelAndView mv = createModelView("news_list");
		PageQuery query = request.init().getQuery();
		NewsCriteria criteria = new NewsCriteria();
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
		List<News> list = newsDAO.queryNews(criteria);
		mv.addObject("query", query);
		mv.addObject("newsList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}

	@RequestMapping("/detail/{newsId}")
	public ModelAndView detail(@PathVariable Long newsId) {
		ModelAndView mv = createModelView("news_detail");
		try {
			News news = newsDAO.get(newsId);
			mv.addObject("news", news);
			mv.addObject("success", news != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		return createModelView("news_add");
	}

	@RequestMapping("/edit/{newsId}")
	public ModelAndView edit(@PathVariable Long newsId) {
		ModelAndView mv = createModelView("news_edit");
		News news = newsDAO.get(newsId);
		mv.addObject("news", news);
		return mv;
	}

	@RequestMapping("/update/{newsId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long newsId, @Valid NewsRequest request, BindingResult result) {
		News news = newsDAO.get(newsId);
		MultipartFile image = request.getCoverImg();
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
			try {
				String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
				news.setCoverImg(path);
			} catch (Exception e) {
				log.error("UploadFile Error.", e);
			}
		}
		news.setArticle(request.getArticle());
		news.setUpdated(new Date());
		news.setStatus(request.getStatus());
		news.setSynopsis(request.getSynopsis());
		news.setTitle(request.getTitle());
		news.setType(request.getType());
		newsDAO.update(news);
		return new ModelAndView("redirect:/news/detail/" + newsId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid NewsRequest request, BindingResult result) {
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
		News news = new News();
		news.setCoverImg(path);
		news.setAdminUserId(0L);
		news.setArticle(request.getArticle());
		news.setClicks(0L);
		Date time = new Date();
		news.setCreated(time);
		news.setUpdated(time);
		news.setStatus(request.getStatus());
		news.setSynopsis(request.getSynopsis());
		news.setTitle(request.getTitle());
		news.setType(request.getType());
		newsDAO.save(news);
		return new ModelAndView("redirect:/news/detail/" + news.getId());
	}
}