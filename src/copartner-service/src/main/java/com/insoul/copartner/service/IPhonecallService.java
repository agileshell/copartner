package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;

public interface IPhonecallService {

	void call(Long orderId) throws CException;
}
