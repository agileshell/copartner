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

import com.insoul.copartner.dao.criteria.InvestOrgCriteria;
import com.insoul.copartner.domain.InvestOrg;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.InvestOrgListRequest;
import com.insoul.ti.req.InvestOrgRequest;
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
@RequestMapping("/investorg")
@Permission("authc")
public class InvestOrgController extends WebBase {

	private static final String INVESTORG_EDIT = "investorg_edit";
	private static final String INVESTORG_ADD = "investorg_add";
	private static final String INVESTORG_DETAIL = "investorg_detail";
	private static final String INVESTORG_LIST = "investorg_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid InvestOrgListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(INVESTORG_LIST, request);
		PageQuery query = request.init().getQuery();
		InvestOrgCriteria criteria = new InvestOrgCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setName(request.getName());
		List<InvestOrg> list = investOrgDAO.queryInvestOrg(criteria);
		Long count = investOrgDAO.countInvestOrg(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("investOrgList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}

	@RequestMapping("/detail/{investOrgId}")
	public ModelAndView detail(@PathVariable Long investOrgId, ViewRequest req) {
		ModelAndView mv = createModelView(INVESTORG_DETAIL, req);
		mv.addObject("viewname", INVESTORG_LIST);
		try {
		    InvestOrg investOrg = investOrgDAO.get(investOrgId);
			mv.addObject("investOrg", investOrg);
			mv.addObject("success", investOrg != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(INVESTORG_ADD, req);
		mv.addObject("viewname", INVESTORG_LIST);
		return mv;
	}

	@RequestMapping("/edit/{investOrgId}")
	public ModelAndView edit(@PathVariable Long investOrgId, ViewRequest req) {
		ModelAndView mv = createModelView(INVESTORG_EDIT, req);
		InvestOrg investOrg = investOrgDAO.get(investOrgId);
        mv.addObject("investOrg", investOrg);
		mv.addObject("viewname", INVESTORG_LIST);
		return mv;
	}

	@RequestMapping("/update/{investOrgId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long investOrgId, @Valid InvestOrgRequest request, BindingResult result) {
	    InvestOrg investOrg = investOrgDAO.get(investOrgId);
	    MultipartFile image = request.getLogo();
        if (image != null) {
            String fileType = FileUtil.getFileType(image.getOriginalFilename());
            if (StringUtils.isNoneBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
                    if (StringUtils.isNoneBlank(path)) investOrg.setLogo(path);
                } catch (Exception e) {
                    log.error("UploadFile Error.", e);
                }
            }
        }
	    investOrg.setContent(request.getArticle());
        investOrg.setHardware(request.getHardware());
        investOrg.setName(request.getName());
        investOrg.setSpecials(request.getSpecials());
        investOrg.setUpdated(new Date());
	    investOrgDAO.update(investOrg);
		return new ModelAndView("redirect:/investorg/detail/" + investOrgId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid InvestOrgRequest request, BindingResult result) {
	    InvestOrg investOrg = new InvestOrg();
        MultipartFile image = request.getLogo();
        if (image != null) {
            String fileType = FileUtil.getFileType(image.getOriginalFilename());
            if (StringUtils.isNoneBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
                    if (StringUtils.isNoneBlank(path)) investOrg.setLogo(path);
                } catch (Exception e) {
                    log.error("UploadFile Error.", e);
                }
            }
        }
	    investOrg.setContent(request.getArticle());
	    investOrg.setHardware(request.getHardware());
	    investOrg.setName(request.getName());
	    investOrg.setSpecials(request.getSpecials());
        Date date = new Date();
        investOrg.setCreated(date);
	    investOrg.setUpdated(date);
	    investOrgDAO.save(investOrg);
		return new ModelAndView("redirect:/investorg/detail/" + investOrg.getId());
	}
}