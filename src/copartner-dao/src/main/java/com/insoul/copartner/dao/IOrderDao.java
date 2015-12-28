package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.OrderCriteria;
import com.insoul.copartner.domain.Order;

public interface IOrderDao extends IBaseDao<Order, Long> {

	List<Order> queryOrder(OrderCriteria criteria);

	Long countOrder(OrderCriteria criteria);
}
