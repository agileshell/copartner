package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.CourseCriteria;
import com.insoul.copartner.domain.Course;

public interface ICourseDao extends IBaseDao<Course, Long> {

    List<Course> queryCourse(CourseCriteria criteria);

    Long countCourse(CourseCriteria criteria);
}
