package com.insoul.copartner.dao;

import com.insoul.copartner.domain.PhoneAccount;

public interface IPhoneAccountDao extends IBaseDao<PhoneAccount, Long> {

	PhoneAccount findByMobile(String mobile);
}
