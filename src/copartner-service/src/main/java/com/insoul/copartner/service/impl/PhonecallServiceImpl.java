package com.insoul.copartner.service.impl;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IOrderDao;
import com.insoul.copartner.dao.IPhoneAccountDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.domain.Order;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
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

		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setSubAccount("7c46ef35b84e11e59288ac853d9f54f2", "f12b97cbf0bf0e377f73a4c681a7988f");// 初始化子帐号和子帐号TOKEN
		restAPI.setAppId("8a48b55151f715fb01520aa18d461d58");// 初始化应用ID
		result = restAPI.callback(mobile, tutor.getMobile(), "4008003127", "4008003127", null, null, null, null,
				order.getMinutes() * 60 + "", null, null, null, "30", null);
		// result = restAPI.callback("主叫号码", "被叫号码", "被叫侧显示的号码", "主叫侧显示的号码",
		// "xx.wav(可选第三方自定义回拨提示音)", "是否一直播放提示音",
		// "用于终止播放提示音的按键","第三方私有数据","最大通话时长","实时话单通知地址", "是否给主被叫发送话单",
		// "是否录音", "通话倒计时", "到达倒计时时间播放的提示音");
		log.info("SDKTestCallback result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			order.setUpdated(new Date());
			order.setStatus("used");
			orderDao.update(order);
		} else {
			log.error("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
			throw CExceptionFactory.getException(CException.class, ResponseCode.CALL_ERROR);
		}
	}

}
