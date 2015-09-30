package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.ICourseDao;
import com.insoul.copartner.dao.criteria.CourseCriteria;
import com.insoul.copartner.domain.Course;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.ICourseService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.CourseDetailVO;
import com.insoul.copartner.vo.CourseVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.CourseAddRequest;
import com.insoul.copartner.vo.request.CourseListRequest;

@Service
public class CourseServiceImpl extends BaseServiceImpl implements ICourseService {

    @Resource
    private ICourseDao courseDao;

    @Override
    public Pagination<CourseVO> listCourses(CourseListRequest requestData) {
        List<CourseVO> courseVOs = new ArrayList<CourseVO>();

        CourseCriteria courseCriteria = new CourseCriteria();
        courseCriteria.setStatus(new String[] { "active" });
        courseCriteria.setKeyword(requestData.getKeyword());
        courseCriteria.setOffset(requestData.getOffset());
        courseCriteria.setLimit(requestData.getLimit());
        courseCriteria.setIsFree(requestData.getIsFree());
        courseCriteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData
                .getFrom()) : null);
        courseCriteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo())
                : null);

        Long count = courseDao.countCourse(courseCriteria);
        List<Course> courses = courseDao.queryCourse(courseCriteria);
        for (Course course : courses) {
            CourseVO courseVO = new CourseVO();
            courseVO.setCourseId(course.getId());
            courseVO.setName(course.getName());
            courseVO.setSynopsis(course.getSynopsis());
            courseVO.setSpeaker(course.getSpeaker());
            courseVO.setTime(course.getTime());
            courseVO.setClicks(course.getClicks());
            courseVO.setCreated(course.getCreated());

            courseVOs.add(courseVO);
        }

        return new Pagination<CourseVO>(courseVOs, count);
    }

    @Override
    public CourseDetailVO getCourse(Long courseId) throws CException {
        Course course = courseDao.get(courseId);
        if (null == course || !course.getStatus().equals("active")) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.COURSE_NOT_EXIST);
        }

        CourseDetailVO courseVO = new CourseDetailVO();
        courseVO.setCourseId(course.getId());
        courseVO.setName(course.getName());
        courseVO.setSynopsis(course.getSynopsis());
        courseVO.setSpeaker(course.getSpeaker());
        courseVO.setUrl(CDNUtil.getFullPath(course.getUrl()));
        courseVO.setTime(course.getTime());
        courseVO.setClicks(course.getClicks());
        courseVO.setCreated(course.getCreated());

        return courseVO;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void createCourse(CourseAddRequest requestData) {
        Course course = new Course();
        course.setUserId(getUserId());
        course.setName(requestData.getName());
        course.setSynopsis(requestData.getSynopsis());
        course.setSpeaker(requestData.getSpeaker());
        course.setUrl(requestData.getUrl());
        course.setTime(requestData.getTime());
        course.setCreated(new Date());

        courseDao.save(course);
    }
}
