package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.CourseDetailVO;
import com.insoul.copartner.vo.CourseVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.CourseAddRequest;
import com.insoul.copartner.vo.request.CourseListRequest;

public interface ICourseService {

    Pagination<CourseVO> listCourses(CourseListRequest requestData);

    CourseDetailVO getCourse(Long courseId) throws CException;

    void createCourse(CourseAddRequest requestData);
}
