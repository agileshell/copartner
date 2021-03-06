package com.insoul.ti.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.FeedbackCriteria;
import com.insoul.copartner.domain.Feedback;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.FeedbackListRequest;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/feedback")
@Permission("authc")
public class FeedbackController extends WebBase {

	private static final String FEEDBACK_LIST = "feedback_list";

	@RequestMapping("/list")
	public ModelAndView list(@Valid FeedbackListRequest request, BindingResult result) {
		ModelAndView mv = createModelView(FEEDBACK_LIST, request);
		PageQuery query = request.init().getQuery();
		FeedbackCriteria criteria = new FeedbackCriteria();
		criteria.setLimit(query.getPage_size());
		criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
		criteria.setUserId(request.getUserId());
		criteria.setText(request.getText());
		List<Feedback> list = feedbackDAO.query(criteria);
        Long count = feedbackDAO.count(criteria);
        query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
		mv.addObject("query", query);
		mv.addObject("feedbackList", list);
		mv.addObject("success", CollectionUtils.isNotEmpty(list));
		mv.addObject("req", request);
		return mv;
	}
}