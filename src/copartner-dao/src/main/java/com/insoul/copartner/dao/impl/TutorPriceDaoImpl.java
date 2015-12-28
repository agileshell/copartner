package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.ITutorPriceDao;
import com.insoul.copartner.domain.TutorPrice;

@Repository
public class TutorPriceDaoImpl extends BaseDaoImpl<TutorPrice, Long>implements ITutorPriceDao {

	@Override
	public TutorPrice getByTutorId(Long tutorId) {
		if (tutorId == null || tutorId <= 0L) {
			return null;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("tutorId", tutorId);
		@SuppressWarnings("unchecked")
		List<TutorPrice> list = createNamedQuery("TutorPrice.getByTutorId", args).getResultList();

		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

}
