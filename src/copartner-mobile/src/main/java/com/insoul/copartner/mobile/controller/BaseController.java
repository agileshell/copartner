package com.insoul.copartner.mobile.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.service.IContentService;
import com.insoul.copartner.service.INewsService;

public abstract class BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    protected INewsService newsService;

    @Autowired
    protected IContentService contentService;

    @Autowired
    protected HttpSession session;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        PropertyEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ResponseCode code = ResponseCode.SERVER_ERROR;
        String errorMessage = null;

        if (ex instanceof CException) {
            code = ((CException) ex).getCode() != null ? ((CException) ex).getCode() : code;
            errorMessage = ex.getMessage();
        } else {
            errorMessage = "server error";
        }

        ModelAndView mav = new ModelAndView();
        if (code.equals(ResponseCode.UNAUTHORIZED)) {
            mav.setViewName("redirect:/login");
        } else {
            mav.setViewName("redirect:/crash");
            mav.addObject("errorMessage", errorMessage);
        }

        return mav;
    }

}
