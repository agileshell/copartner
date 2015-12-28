package com.insoul.copartner.dao;

import com.insoul.copartner.domain.TutorPrice;

public interface ITutorPriceDao extends IBaseDao<TutorPrice, Long> {

	TutorPrice getByTutorId(Long tutorId);
}
