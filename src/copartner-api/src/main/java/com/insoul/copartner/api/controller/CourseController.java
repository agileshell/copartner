package com.insoul.copartner.api.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.ICourseService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.CourseAddRequest;
import com.insoul.copartner.vo.request.CourseListRequest;

@Controller
public class CourseController extends BaseController {

    @Resource
    private ICourseService courseService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listCourses(@Valid CourseListRequest requestData, BindingResult result)
            throws DataValidationException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        return ResponseUtil.jsonSucceed(courseService.listCourses(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{courseId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getQuestion(@PathVariable Long courseId) throws CException {
        return ResponseUtil.jsonSucceed(courseService.getCourse(courseId), HttpStatus.OK);
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createCourse(@Valid CourseAddRequest requestData, BindingResult result)
            throws CException {
        if (result.hasErrors()) {
            throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
        }

        courseService.createCourse(requestData);

        return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
    }
}
