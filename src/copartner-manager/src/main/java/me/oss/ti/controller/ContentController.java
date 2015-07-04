package me.oss.ti.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.ContentCriteria;
import com.insoul.copartner.domain.Content;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;

import me.oss.ti.WebBase;
import me.oss.ti.req.ContentListRequest;
import me.oss.ti.req.ContentRequest;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月26日 下午4:10:58
 */
@Controller
@RequestMapping("/content")
public class ContentController extends WebBase {

	@RequestMapping("/list")
	public ModelAndView list(@Valid ContentListRequest request, BindingResult result) {
		ModelAndView mv = createModelView("content_list");
		ContentCriteria criteria = new ContentCriteria();
		criteria.setLimit(request.getLimit());
		criteria.setOffset(request.getOffset());
		criteria.setType(request.getType());
		List<Content> list = contentDAO.queryContent(criteria);
		mv.addObject("contentList", list);
		return mv;
	}

	@RequestMapping("/detail/{contentId}")
	public ModelAndView detail(@PathVariable Long contentId) {
		ModelAndView mv = createModelView("content_detail");
		Content content = contentDAO.get(contentId);
		mv.addObject("content", content);
		return mv;
	}

	@RequestMapping("/edit/{contentId}")
	public ModelAndView edit(@PathVariable Long contentId, @Valid ContentRequest request, BindingResult result) {
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

	@RequestMapping("/add")
	public ModelAndView add(@Valid ContentRequest request, BindingResult result) {
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
		content.setStatus("inactive");
		content.setSynopsis(request.getSynopsis());
		content.setTitle(request.getTitle());
		content.setType(request.getType());
		contentDAO.save(content);
		return new ModelAndView("redirect:/content/detail/" + content.getId());
	}
}