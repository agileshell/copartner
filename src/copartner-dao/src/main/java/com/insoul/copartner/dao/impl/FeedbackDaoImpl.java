package com.insoul.copartner.dao.impl;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IFeedbackDao;
import com.insoul.copartner.domain.Feedback;

@Repository
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Long> implements IFeedbackDao {

}
