package com.insoul.copartner.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IPhoneAccountDao;
import com.insoul.copartner.domain.PhoneAccount;

@Repository
public class PhoneAccountDaoImpl extends BaseDaoImpl<PhoneAccount, Long>implements IPhoneAccountDao {

	@Override
	public PhoneAccount findByMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("mobile", mobile);
		@SuppressWarnings("unchecked")
		List<PhoneAccount> list = createNamedQuery("PhoneAccount.findByMobile", args).getResultList();

		return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
	}

}
