package com.insoul.copartner.api.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.exception.DataValidationException;
import com.insoul.copartner.service.IOrderService;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.request.OrderCreateRequest;
import com.insoul.copartner.vo.request.OrderListRequest;

@Controller
public class OrderController extends BaseController {

	@Resource
	private IOrderService orderService;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listOrder(@Valid OrderListRequest requestData, BindingResult result)
			throws CException {
		if (result.hasErrors()) {
			throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
		}

		return ResponseUtil.jsonSucceed(orderService.listOrder(requestData), HttpStatus.OK);
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createOrder(@Valid OrderCreateRequest requestData, BindingResult result)
			throws CException {
		if (result.hasErrors()) {
			throw CExceptionFactory.getException(DataValidationException.class, ResponseCode.INVALID_PARAMETER);
		}

		orderService.create(requestData);

		return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
	}
}
