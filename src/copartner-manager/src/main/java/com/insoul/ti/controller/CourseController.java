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

import com.insoul.copartner.dao.criteria.CourseCriteria;
import com.insoul.copartner.domain.Course;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.FileUtil;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.CourseListRequest;
import com.insoul.ti.req.CourseRequest;
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
@RequestMapping("/course")
@Permission("authc")
public class CourseController extends WebBase {

	private static final String COURSE_EDIT = "course_edit";
	private static final String COURSE_ADD = "course_add";
	private static final String COURSE_DETAIL = "course_detail";
	private static final String COURSE_LIST = "course_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid CourseListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(COURSE_LIST, request);
		PageQuery query = request.init().getQuery();
		CourseCriteria criteria = new CourseCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(query.getIndex());
		String[] status = null;
		if (StringUtils.isNotBlank(request.getStatus())) {
			status = new String[] { request.getStatus() };
		}
		criteria.setStatus(status);
		criteria.setKeyword(request.getKeyword());
		List<Course> list = courseDAO.queryCourse(criteria);
        Long count = courseDAO.countCourse(criteria);
        query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("coursetList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}
	
	@RequestMapping("/update_status/{courseId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView updateStatus(@PathVariable Long courseId, @RequestParam(value = "status", required = true) String status) {
		try {
		    Course course = courseDAO.get(courseId);
		    course.setStatus(status);
		    courseDAO.update(course);
			returnJson(true, "200", "修改成功!!");
		} catch (Exception e) {
			returnJson(true, "500", "修改失败!!");
		}
		return null;
	}

	@RequestMapping("/detail/{courseId}")
	public ModelAndView detail(@PathVariable Long courseId, ViewRequest req) {
		ModelAndView mv = createModelView(COURSE_DETAIL, req);
		mv.addObject("viewname", COURSE_LIST);
		try {
		    Course course = courseDAO.get(courseId);
			mv.addObject("course", course);
			mv.addObject("success", course != null);
		} catch (Exception e) {
			mv.addObject("success", false);
		}
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView add(ViewRequest req) {
		ModelAndView mv = createModelView(COURSE_ADD, req);
		mv.addObject("viewname", COURSE_LIST);
		return mv;
	}

	@RequestMapping("/edit/{courseId}")
	public ModelAndView edit(@PathVariable Long courseId, ViewRequest req) {
		ModelAndView mv = createModelView(COURSE_EDIT, req);
		Course course = courseDAO.get(courseId);
		mv.addObject("course", course);
		mv.addObject("viewname", COURSE_LIST);
		return mv;
	}

	@RequestMapping("/update/{courseId}")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView update(@PathVariable Long courseId, @Valid CourseRequest request, BindingResult result) {
	    Course course = courseDAO.get(courseId);
        course.setIsFree(request.getFree() > 0);
        course.setName(request.getName());
        course.setSpeaker(request.getSpeaker());
        course.setStatus(request.getStatus());
        course.setSynopsis(request.getSynopsis());
        course.setTime(request.getTime());
        MultipartFile coverImg = request.getCoverImg();
        String url = StringUtils.EMPTY;
        if (coverImg != null) {
            String fileType = FileUtil.getFileType(coverImg.getOriginalFilename());
            if (StringUtils.isNotBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    url = CDNUtil.uploadFile(coverImg.getInputStream(), fileName);
                    if (StringUtils.isNotBlank(url)) course.setCoverImg(url);
                } catch (Exception e) {
                    log.error("UploadFile Media Error.", e);
                }
            }
        }
        if (StringUtils.isNotBlank(request.getMedia())) {
            course.setUrl(request.getMedia());
        }
        course.setUpdated(new Date());
        courseDAO.update(course);
		return new ModelAndView("redirect:/course/detail/" + courseId);
	}

	@RequestMapping("/save")
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public ModelAndView save(@Valid CourseRequest request, BindingResult result) {
        Date date = new Date();
		Course course = new Course();
		course.setClicks(0L);
        course.setCreated(date);
		course.setIsFree(request.getFree() > 0);
		course.setName(request.getName());
		course.setSpeaker(request.getSpeaker());
		course.setStatus(request.getStatus());
		course.setSynopsis(request.getSynopsis());
		course.setTime(request.getTime());
		course.setUpdated(date);
		MultipartFile coverImg = request.getCoverImg();
		String url = StringUtils.EMPTY;
		if (coverImg != null) {
            String fileType = FileUtil.getFileType(coverImg.getOriginalFilename());
            if (StringUtils.isNotBlank(fileType)) {
                String fileName = new StringBuilder().append(UUID.randomUUID()).append(".").append(fileType).toString();
                try {
                    url = CDNUtil.uploadFile(coverImg.getInputStream(), fileName);
                    if (StringUtils.isNotBlank(url)) course.setCoverImg(url);
                } catch (Exception e) {
                    log.error("UploadFile Media Error.", e);
                }
            }
        }
        if (StringUtils.isNotBlank(request.getMedia())) {
            course.setUrl(request.getMedia());
        }
		course.setUserId(0L);
		courseDAO.save(course);
		return new ModelAndView("redirect:/course/detail/" + course.getId());
	}
}