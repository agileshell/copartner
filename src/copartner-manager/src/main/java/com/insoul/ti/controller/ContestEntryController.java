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

import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.ContestEntry;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.ContestEntryListRequest;
import com.insoul.ti.req.ContestEntryRequest;
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
@RequestMapping("/contestentry")
@Permission("authc")
public class ContestEntryController extends WebBase {

	private static final String CONTEST_ENTRY_EDIT = "contestentry_edit";
	private static final String CONTEST_ENTRY_ADD = "contestentry_add";
	private static final String CONTEST_ENTRY_DETAIL = "contestentry_detail";
	private static final String CONTEST_ENTRY_LIST = "contestentry_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid ContestEntryListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(CONTEST_ENTRY_LIST, request);
		PageQuery query = request.init().getQuery();
		ContestEntryCriteria criteria = new ContestEntryCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setContestId(request.getContestId());
		criteria.setName(request.getName());
		List<ContestEntry> list = contestEntryDAO.queryContestEntry(criteria);
		Long count = contestEntryDAO.countContestEntry(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("contestEntryList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}
	
	@RequestMapping("/update_status/{contestEntryId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView updateStatus(@PathVariable Long contestEntryId, @RequestParam(value = "status", required = true) String status) {
		try {
		    ContestEntry contestEntry = contestEntryDAO.get(contestEntryId);
		    contestEntry.setStatus(status);
		    contestEntryDAO.update(contestEntry);
			returnJson(true, "200", "修改成功!!");
		} catch (Exception e) {
			returnJson(true, "500", "修改失败!!");
		}
		return null;
	}

	@RequestMapping("/detail/{contestEntryId}")
	public ModelAndView detail(@PathVariable Long contestEntryId, ViewRequest req) {
		ModelAndView mv = createModelView(CONTEST_ENTRY_DETAIL, req);
		mv.addObject("viewname", CONTEST_ENTRY_LIST);
		try {
		    ContestEntry contestEntry = contestEntryDAO.get(contestEntryId);
			mv.addObject("contestEntry", contestEntry);
			mv.addObject("success", contestEntry != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(CONTEST_ENTRY_ADD, req);
		mv.addObject("viewname", CONTEST_ENTRY_LIST);
		return mv;
	}

	@RequestMapping("/edit/{contestEntryId}")
	public ModelAndView edit(@PathVariable Long contestEntryId, ViewRequest req) {
		ModelAndView mv = createModelView(CONTEST_ENTRY_EDIT, req);
		ContestEntry contestEntry = contestEntryDAO.get(contestEntryId);
		mv.addObject("contestEntry", contestEntry);
		mv.addObject("viewname", CONTEST_ENTRY_LIST);
		return mv;
	}

	@RequestMapping("/update/{contestEntryId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long contestEntryId, @Valid ContestEntryRequest request, BindingResult result) {
	    ContestEntry contestEntry = contestEntryDAO.get(contestEntryId);
		MultipartFile image = request.getCoverImg();
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			if (StringUtils.isNotBlank(fileType)) {
			    String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
	            try {
	                String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
	                if (StringUtils.isNotBlank(path)) contestEntry.setCoverImg(path);
	            } catch (Exception e) {
	                log.error("UploadFile Error.", e);
	            }
			}
		}
		contestEntry.setUpdated(new Date());
		contestEntry.setStatus(request.getStatus());
        contestEntry.setIntroduction(request.getIntroduction());
        contestEntry.setContact(request.getContact());
        contestEntry.setContestId(request.getContestId());
        contestEntry.setIntroduction(request.getIntroduction());
        contestEntry.setName(request.getName());
        contestEntry.setUserName(request.getUserName());
        contestEntryDAO.update(contestEntry);
		return new ModelAndView("redirect:/contestentry/detail/" + contestEntryId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid ContestEntryRequest request, BindingResult result) {
		MultipartFile image = request.getCoverImg();
		String path = StringUtils.EMPTY;
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			if (StringUtils.isNotBlank(fileType)) {
			    String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
	            try {
	                path = CDNUtil.uploadFile(image.getInputStream(), fileName);
	            } catch (Exception e) {
	                log.error("UploadFile Error.", e);
	            }
			}
		}
		ContestEntry contestEntry = new ContestEntry();
		if (StringUtils.isNotBlank(path)) contestEntry.setCoverImg(path);
		Date time = new Date();
		contestEntry.setUpdated(time);
		contestEntry.setCreated(time);
        contestEntry.setStatus(request.getStatus());
        contestEntry.setContact(request.getContact());
        contestEntry.setContestId(request.getContestId());
        contestEntry.setIntroduction(request.getIntroduction());
        contestEntry.setName(request.getName());
        contestEntry.setUserName(request.getUserName());
        contestEntryDAO.save(contestEntry);
		return new ModelAndView("redirect:/contestentry/detail/" + contestEntry.getId());
	}
}