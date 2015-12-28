package com.insoul.copartner.service;

import com.insoul.copartner.vo.OrderVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.OrderCreateRequest;
import com.insoul.copartner.vo.request.OrderListRequest;

public interface IOrderService {

	Pagination<OrderVO> listOrder(OrderListRequest requestData);

	void create(OrderCreateRequest requestData);
}
