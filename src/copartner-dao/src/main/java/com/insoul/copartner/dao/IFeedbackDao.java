package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.FeedbackCriteria;
import com.insoul.copartner.domain.Feedback;

public interface IFeedbackDao extends IBaseDao<Feedback, Long> {

	List<Feedback> query(FeedbackCriteria criteria);
}