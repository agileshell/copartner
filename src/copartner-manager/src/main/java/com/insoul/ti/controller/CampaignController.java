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

import com.insoul.copartner.dao.criteria.CampaignCriteria;
import com.insoul.copartner.domain.Campaign;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.CampaignListRequest;
import com.insoul.ti.req.CampaignRequest;
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
@RequestMapping("/campaign")
@Permission("authc")
public class CampaignController extends WebBase {

	private static final String CAMPAIGN_EDIT = "campaign_edit";
	private static final String CAMPAIGN_ADD = "campaign_add";
	private static final String CAMPAIGN_DETAIL = "campaign_detail";
	private static final String CAMPAIGN_LIST = "campaign_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid CampaignListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(CAMPAIGN_LIST, request);
		PageQuery query = request.init().getQuery();
		CampaignCriteria criteria = new CampaignCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		String[] status = null;
		if (StringUtils.isNotBlank(request.getStatus())) {
			status = new String[] { request.getStatus() };
		}
		criteria.setStatus(status);
		criteria.setId(request.getId());
		criteria.setTitle(request.getTitle());
		List<Campaign> list = campaignDAO.queryCampaign(criteria);
		Long count = campaignDAO.countCampaign(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("campaignList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}
	
	@RequestMapping("/update_status/{campaignId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView updateStatus(@PathVariable Long campaignId, @RequestParam(value = "status", required = true) String status) {
		try {
		    Campaign campaign = campaignDAO.get(campaignId);
		    campaign.setStatus(status);
		    campaignDAO.update(campaign);
			returnJson(true, "200", "修改成功!!");
		} catch (Exception e) {
			returnJson(true, "500", "修改失败!!");
		}
		return null;
	}

	@RequestMapping("/detail/{campaignId}")
	public ModelAndView detail(@PathVariable Long campaignId, ViewRequest req) {
		ModelAndView mv = createModelView(CAMPAIGN_DETAIL, req);
		mv.addObject("viewname", CAMPAIGN_LIST);
		try {
		    Campaign campaign = campaignDAO.get(campaignId);
			mv.addObject("campaign", campaign);
			mv.addObject("success", campaign != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(CAMPAIGN_ADD, req);
		mv.addObject("viewname", CAMPAIGN_LIST);
		return mv;
	}

	@RequestMapping("/edit/{campaignId}")
	public ModelAndView edit(@PathVariable Long campaignId, ViewRequest req) {
		ModelAndView mv = createModelView(CAMPAIGN_EDIT, req);
		Campaign campaign = campaignDAO.get(campaignId);
		mv.addObject("campaign", campaign);
		mv.addObject("viewname", CAMPAIGN_LIST);
		return mv;
	}

	@RequestMapping("/update/{campaignId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long campaignId, @Valid CampaignRequest request, BindingResult result) {
	    Campaign campaign = campaignDAO.get(campaignId);
		MultipartFile image = request.getCoverImg();
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			if (StringUtils.isNotBlank(fileType)) {
			    String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
	            try {
	                String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
	                if (StringUtils.isNotBlank(path)) campaign.setCoverImg(path);
	            } catch (Exception e) {
	                log.error("UploadFile Error.", e);
	            }
			}
		}
		campaign.setContent(request.getContent());
		campaign.setUpdated(new Date());
		campaign.setStatus(request.getStatus());
		campaign.setTitle(request.getTitle());
		campaignDAO.update(campaign);
		return new ModelAndView("redirect:/campaign/detail/" + campaignId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid CampaignRequest request, BindingResult result) {
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
		Campaign campaign = new Campaign();
		if (StringUtils.isNotBlank(path)) campaign.setCoverImg(path);
		Date time = new Date();
		campaign.setCreated(time);
		campaign.setUpdated(time);
		campaign.setContent(request.getContent());
        campaign.setStatus(request.getStatus());
        campaign.setTitle(request.getTitle());
		campaignDAO.save(campaign);
		return new ModelAndView("redirect:/campaign/detail/" + campaign.getId());
	}
}