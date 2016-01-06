package com.insoul.copartner.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.dao.IOrderDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.OrderCriteria;
import com.insoul.copartner.domain.Order;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.service.IOrderService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.OrderVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.OrderCreateRequest;
import com.insoul.copartner.vo.request.OrderListRequest;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {

	@Resource
	private IOrderDao orderDao;

	@Resource
	private IUserDao userDao;

	@Override
	public Pagination<OrderVO> listOrder(OrderListRequest requestData) {
		List<OrderVO> orderVOs = new ArrayList<OrderVO>();

		OrderCriteria criteria = new OrderCriteria();
		criteria.setOffset(requestData.getOffset());
		criteria.setLimit(requestData.getLimit());
		criteria.setFrom(
				(null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
		criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
		criteria.setUserId(getUserId());

		Long count = orderDao.countOrder(criteria);
		List<Order> orders = orderDao.queryOrder(criteria);
		for (Order order : orders) {
			OrderVO orderVO = new OrderVO();
			orderVO.setId(order.getId());
			orderVO.setTutorId(order.getTutorId());
			orderVO.setUserName(order.getUserName());
			orderVO.setUserMobile(order.getUserMobile());
			orderVO.setUserQuestion(order.getUserQuestion());
			orderVO.setMinutes(order.getMinutes());
			orderVO.setPaymentType(order.getPaymentType());
			orderVO.setAmount(order.getAmount());
			orderVO.setStatus(order.getStatus());
			orderVO.setCreated(order.getCreated());

			orderVO.setTutorId(order.getTutorId());
			User tutor = userDao.get(order.getTutorId());
			if (null != tutor) {
				orderVO.setTutorName(tutor.getName());
				orderVO.setTutorAvatar(CDNUtil.getFullPath(tutor.getAvatar()));
			}

			orderVOs.add(orderVO);
		}

		return new Pagination<OrderVO>(orderVOs, count);
	}

	@Override
	@Transactional(value = "transactionManager", rollbackFor = Throwable.class)
	public void create(OrderCreateRequest requestData) {
		Order order = new Order();
		order.setTutorId(requestData.getTutorId());
		order.setUserId(getUserId());
		order.setUserName(requestData.getUserName());
		order.setUserMobile(requestData.getUserMobile());
		order.setUserQuestion(requestData.getUserQuestion());
		order.setPaymentType("wechat");
		// TODO
		order.setStatus("paid");
		order.setMinutes(5);
		order.setAmount(new BigDecimal(0.00));
		order.setCreated(new Date());

		orderDao.save(order);
	}

}
