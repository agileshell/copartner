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

import com.insoul.copartner.dao.criteria.ContestCriteria;
import com.insoul.copartner.domain.Contest;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.ContestListRequest;
import com.insoul.ti.req.ContestRequest;
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
@RequestMapping("/contest")
@Permission("authc")
public class ContestController extends WebBase {

	private static final String CONTEST_EDIT = "contest_edit";
	private static final String CONTEST_ADD = "contest_add";
	private static final String CONTEST_DETAIL = "contest_detail";
	private static final String CONTEST_LIST = "contest_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid ContestListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(CONTEST_LIST, request);
		PageQuery query = request.init().getQuery();
		ContestCriteria criteria = new ContestCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setStatus(request.getStatus());
		criteria.setTitle(request.getTitle());
		List<Contest> list = contestDAO.queryContest(criteria);
		Long count = contestDAO.countContest(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("contestList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}
	
	@RequestMapping("/update_status/{contestId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView updateStatus(@PathVariable Long contestId, @RequestParam(value = "status", required = true) String status) {
		try {
			Contest contest = contestDAO.get(contestId);
			contest.setStatus(status);
			contestDAO.update(contest);
			returnJson(true, "200", "修改成功!!");
		} catch (Exception e) {
			returnJson(true, "500", "修改失败!!");
		}
		return null;
	}

	@RequestMapping("/detail/{contestId}")
	public ModelAndView detail(@PathVariable Long contestId, ViewRequest req) {
		ModelAndView mv = createModelView(CONTEST_DETAIL, req);
		mv.addObject("viewname", CONTEST_LIST);
		try {
		    Contest contest = contestDAO.get(contestId);
			mv.addObject("contest", contest);
			mv.addObject("success", contest != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(CONTEST_ADD, req);
		mv.addObject("viewname", CONTEST_LIST);
		return mv;
	}

	@RequestMapping("/edit/{contestId}")
	public ModelAndView edit(@PathVariable Long contestId, ViewRequest req) {
		ModelAndView mv = createModelView(CONTEST_EDIT, req);
		Contest contest = contestDAO.get(contestId);
		mv.addObject("contest", contest);
		mv.addObject("viewname", CONTEST_LIST);
		return mv;
	}

	@RequestMapping("/update/{contestId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long contestId, @Valid ContestRequest request, BindingResult result) {
	    Contest contest = contestDAO.get(contestId);
		MultipartFile image = request.getCoverImg();
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			if (StringUtils.isNotBlank(fileType)) {
			    String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
	            try {
	                String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
	                if (StringUtils.isNotBlank(path)) contest.setCoverImg(path);
	            } catch (Exception e) {
	                log.error("UploadFile Error.", e);
	            }
			}
		}
		contest.setUpdated(new Date());
		contest.setStatus(request.getStatus());
		contest.setTitle(request.getTitle());
		contest.setIntroduction(request.getIntroduction());
        contest.setRules(request.getRules());
        contest.setRegistration(request.getRegistration());
		contestDAO.update(contest);
		return new ModelAndView("redirect:/contest/detail/" + contestId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid ContestRequest request, BindingResult result) {
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
		Contest contest = new Contest();
		if (StringUtils.isNotBlank(path)) contest.setCoverImg(path);
		Date time = new Date();
		contest.setUpdated(time);
		contest.setCreated(time);
        contest.setStatus(request.getStatus());
        contest.setTitle(request.getTitle());
        contest.setIntroduction(request.getIntroduction());
        contest.setRules(request.getRules());
        contest.setRegistration(request.getRegistration());
		contestDAO.save(contest);
		return new ModelAndView("redirect:/contest/detail/" + contest.getId());
	}
}