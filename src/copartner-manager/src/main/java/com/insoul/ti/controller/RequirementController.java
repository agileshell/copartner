package com.insoul.ti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.RequirementCriteria;
import com.insoul.copartner.domain.Requirement;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.RequireListRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;
import com.insoul.ti.vo.RequirementVO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/require")
@Permission("authc")
public class RequirementController extends WebBase {

//	private static final String REQUIRE_EDIT = "require_edit";
//	private static final String REQUIRE_ADD = "require_add";
	private static final String REQUIRE_DETAIL = "require_detail";
	private static final String REQUIRE_LIST = "require_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid RequireListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(REQUIRE_LIST, request);
		PageQuery query = request.init().getQuery();
		RequirementCriteria criteria = new RequirementCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		String[] status = null;
		if (StringUtils.isNotBlank(request.getStatus())) {
			status = new String[] { request.getStatus() };
		}
		criteria.setStatus(status);
		criteria.setType(request.getType());
		criteria.setUserId(request.getUserId());
		List<Requirement> list = requireDAO.queryRequirement(criteria);
		Long count = requireDAO.countRequirement(criteria);
		query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		List<RequirementVO> voList = new ArrayList<RequirementVO>();
		if (CollectionUtils.isNotEmpty(list)) {
		    for (Requirement requirement : list) {
		        voList.add(toRequirementVO(requirement));
	        }
	        mv.addObject("query", query);
	        mv.addObject("requireList", voList);
	        mv.addObject("success", true);
	        mv.addObject("req", request);
		} else {
	        mv.addObject("query", query);
	        mv.addObject("requireList", voList);
	        mv.addObject("success", false);
	        mv.addObject("req", request);
		}
		return mv;
	}
	
	private RequirementVO toRequirementVO(Requirement requirement) {
	    RequirementVO vo = new RequirementVO();
	    vo.setCommentCount(requirement.getCommentCount());
	    vo.setContent(requirement.getContent());
	    vo.setCreated(requirement.getCreated());
	    vo.setId(requirement.getId());
	    vo.setLikeCount(requirement.getLikeCount());
	    vo.setProjectId(requirement.getProjectId());
	    try {
	        vo.setProjectName(projectDAO.getProjectName(requirement.getProjectId()));
        } catch (Exception ingore) {}
	    vo.setStatus(requirement.getStatus());
	    vo.setType(requirement.getType());
	    vo.setUpdated(requirement.getUpdated());
	    try {
	        UserLeanVO user = new UserLeanVO();
	        Long userId = requirement.getUserId();
	        User u = userDAO.get(userId);
	        if (null != u) {
	            user.setUserId(u.getId());
	            user.setName(u.getName());
	            user.setAvatar(CDNUtil.getFullPath(u.getAvatar()));
	        }
	        vo.setUser(user);
        } catch (Exception e) {
        }
	    vo.setUserId(requirement.getUserId());
	    return vo;
	}
	
	@RequestMapping("/update_status/{requireId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView updateStatus(@PathVariable Long requireId, @RequestParam(value = "status", required = true) String status) {
		try {
		    Requirement requirement = requireDAO.get(requireId);
		    requirement.setStatus(status);
			requireDAO.update(requirement);
			returnJson(true, "200", "修改成功!!");
		} catch (Exception e) {
			returnJson(true, "500", "修改失败!!");
		}
		return null;
	}

	@RequestMapping("/detail/{requireId}")
	public ModelAndView detail(@PathVariable Long requireId, ViewRequest req) {
		ModelAndView mv = createModelView(REQUIRE_DETAIL, req);
		mv.addObject("viewname", REQUIRE_LIST);
		try {
		    Requirement require = requireDAO.get(requireId);
			mv.addObject("require", toRequirementVO(require));
			mv.addObject("success", require != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	/**
	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(REQUIRE_ADD, req);
		mv.addObject("viewname", REQUIRE_LIST);
		return mv;
	}

	@RequestMapping("/edit/{contentId}")
	public ModelAndView edit(@PathVariable Long contentId, ViewRequest req) {
		ModelAndView mv = createModelView(REQUIRE_EDIT, req);
		Content content = contentDAO.get(contentId);
		mv.addObject("content", content);
		mv.addObject("viewname", REQUIRE_LIST);
		return mv;
	}

	@RequestMapping("/update/{contentId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long contentId, @Valid ContentRequest request, BindingResult result) {
		Content content = contentDAO.get(contentId);
		MultipartFile image = request.getCoverImg();
		if (image != null) {
			String fileType = FileUtil.getFileType(image.getOriginalFilename());
			if (StringUtils.isNotBlank(fileType)) {
			    String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
	            try {
	                String path = CDNUtil.uploadFile(image.getInputStream(), fileName);
	                if (StringUtils.isNotBlank(path)) content.setCoverImg(path);
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
			if (StringUtils.isNotBlank(fileType)) {
			    String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
	            try {
	                path = CDNUtil.uploadFile(image.getInputStream(), fileName);
	            } catch (Exception e) {
	                log.error("UploadFile Error.", e);
	            }
			}
		}
		Content content = new Content();
		if (StringUtils.isNotBlank(path)) content.setCoverImg(path);
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
	**/
	
}