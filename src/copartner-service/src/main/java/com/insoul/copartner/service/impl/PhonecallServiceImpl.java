package com.insoul.copartner.service.impl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IOrderDao;
import com.insoul.copartner.dao.IPhoneAccountDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.domain.Order;
import com.insoul.copartner.domain.PhoneAccount;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.phonecall.util.Config;
import com.insoul.copartner.phonecall.util.HttpUtil;
import com.insoul.copartner.service.IPhonecallService;

@Service
public class PhonecallServiceImpl extends BaseServiceImpl implements IPhonecallService {

	@Resource
	private IOrderDao orderDao;

	@Resource
	private IPhoneAccountDao phoneAccountDao;

	@Resource
	private IUserDao userDao;

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void call(Long orderId) throws CException {
		try {
			Order order = orderDao.get(orderId);
			if (null == order) {
				throw CExceptionFactory.getException(CException.class, ResponseCode.NOT_BOOK_TUTOR);
			}
			if (order.getStatus().equals("used")) {
				throw CExceptionFactory.getException(CException.class, ResponseCode.CALL_PHONE_HAS_USED);
			}
			User tutor = userDao.get(order.getTutorId());
			if (null == tutor || StringUtils.isBlank(tutor.getMobile())) {
				throw CExceptionFactory.getException(CException.class, ResponseCode.TUTOR_INFO_ERROR);
			}
			String mobile = order.getUserMobile();
			String clientNumber = null;
			String clientPwd = null;
			PhoneAccount caller = phoneAccountDao.findByMobile(mobile);
			if (null == caller) {
				String body = "{\"client\": {\"appId\":\"" + Config.APP_ID + "\",\"friendlyName\":\"" + mobile
						+ "\",\"mobile\":\"" + mobile + "\"}}";
				String result = HttpUtil.post("clients?", body, Config.ACCOUNT_SID, Config.KEY);
				JSONObject json = new JSONObject(result);
				JSONObject resultObj = json.getJSONObject("result");
				if (null != resultObj && resultObj.getString("respCode").equals("00000")) {
					clientNumber = resultObj.getString("clientNumber");
					clientPwd = resultObj.getString("clientPwd");

					PhoneAccount phoneAccount = new PhoneAccount();
					phoneAccount.setFriendlyName(mobile);
					phoneAccount.setMobile(mobile);
					phoneAccount.setClientNumber(clientNumber);
					phoneAccount.setClientPwd(clientPwd);
					phoneAccount.setCreated(new Date());

					phoneAccountDao.save(phoneAccount);
				}
			} else {
				clientNumber = caller.getClientNumber();
				clientPwd = caller.getClientPwd();
			}
			String tutorMobile = tutor.getMobile();
			PhoneAccount called = phoneAccountDao.findByMobile(mobile);
			if (null == called) {
				String body = "{\"client\": {\"appId\":\"" + Config.APP_ID + "\",\"friendlyName\":\"" + tutorMobile
						+ "\",\"mobile\":\"" + tutorMobile + "\"}}";
				String result = HttpUtil.post("clients?", body, Config.ACCOUNT_SID, Config.KEY);
				JSONObject json = new JSONObject(result);
				JSONObject resultObj = json.getJSONObject("result");
				if (null != resultObj && resultObj.getString("respCode").equals("00000")) {
					String clientNumber2 = resultObj.getString("clientNumber");
					String clientPwd2 = resultObj.getString("clientPwd");

					PhoneAccount phoneAccount2 = new PhoneAccount();
					phoneAccount2.setFriendlyName(tutorMobile);
					phoneAccount2.setMobile(tutorMobile);
					phoneAccount2.setClientNumber(clientNumber2);
					phoneAccount2.setClientPwd(clientPwd2);
					phoneAccount2.setCreated(new Date());

					phoneAccountDao.save(phoneAccount2);
				}
			}
			if (StringUtils.isNotBlank(clientNumber) && StringUtils.isNotBlank(clientPwd)) {
				String body = "{ \"callback\" : { \"appId\" : \"" + Config.APP_ID + "\", \"caller\" : \"" + mobile
						+ "\", \"called\" : \"" + tutorMobile + "\"}}";

				String result = HttpUtil.post("call/callBack?", body, clientNumber, clientPwd);
				Matcher m = Pattern.compile("\"respCode\"\\s*:\\s*\"(\\w+)\"").matcher(result);
				if (!m.find() || !m.group(1).equals("00000")) {
					throw CExceptionFactory.getException(CException.class, ResponseCode.CALL_ERROR);
				} else {
					order.setUpdated(new Date());
					order.setStatus("used");
					orderDao.update(order);
				}
			} else {
				throw CExceptionFactory.getException(CException.class, ResponseCode.CALL_ERROR);
			}
		} catch (JSONException e) {
			throw CExceptionFactory.getException(CException.class, ResponseCode.CALL_ERROR);
		}
	}

}
