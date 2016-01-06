package com.insoul.copartner.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.phonecall.model.CallAuthReq;
import com.insoul.copartner.phonecall.model.CallAuthResp;
import com.insoul.copartner.phonecall.model.CallEstablishReq;
import com.insoul.copartner.phonecall.model.CallEstablishResp;
import com.insoul.copartner.phonecall.model.HangupReq;
import com.insoul.copartner.phonecall.model.HangupResp;
import com.insoul.copartner.phonecall.model.RespCode;
import com.insoul.copartner.service.IPhonecallService;
import com.insoul.copartner.util.ResponseUtil;

@Controller
public class PhoneCallController extends BaseController {

	@Resource
	private IPhonecallService phonecallService;

	@RequestMapping(value = "/call/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> call(@PathVariable Long orderId) throws CException {
		phonecallService.call(orderId);

		return ResponseUtil.jsonSucceed(null, HttpStatus.OK);
	}

	/**
	 * 呼叫鉴权
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/callAuth", method = RequestMethod.POST)
	public ResponseEntity<Object> callAuth(HttpServletRequest request) throws IOException {

		// 获取请求参数
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String str = null;
		StringBuffer reqBody = new StringBuffer();
		while ((str = reader.readLine()) != null) {
			reqBody.append(str);
		}
		System.out.println("收到请求：" + reqBody);

		// 将数据注入对应的bean
		Gson gson = new Gson();
		CallAuthReq callAuthReq = gson.fromJson(reqBody.toString(), CallAuthReq.class);

		// TODO 判断签名是否正确

		// TODO 业务处理。开发者根据自己的需求实现

		// 响应
		CallAuthResp callAuthResp = new CallAuthResp();
		callAuthResp.setRespCode(RespCode.SUCCESS);
		callAuthResp.setAllowedCallTime("120");
		callAuthResp.setFromSerNum(callAuthReq.getFromSerNum());
		callAuthResp.setToSerNum(callAuthReq.getToSerNum());
		callAuthResp.setRecord("");
		callAuthResp.setReason("");
		callAuthResp.setNoBalanceTime("30");
		callAuthResp.setCallingAudioId("");
		callAuthResp.setConnectAudioId("");
		callAuthResp.setConnectCalledAudioId("");
		callAuthResp.setConnectTimeoutAudioId("");
		callAuthResp.setLackOftimeAudioId("");

		String respStr = gson.toJson(callAuthResp);
		System.out.println("返回的数据:" + respStr);

		return ResponseUtil.json(callAuthResp, HttpStatus.OK);
	}

	/**
	 * 呼叫建立
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/callEstablish", method = RequestMethod.POST)
	public ResponseEntity<Object> callEstablish(HttpServletRequest request) throws IOException {
		// 获取请求参数
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String str = null;
		StringBuffer reqBody = new StringBuffer();
		while ((str = reader.readLine()) != null) {
			reqBody.append(str);
		}
		System.out.println("收到请求：" + reqBody);

		// 将数据注入对应的bean
		Gson gson = new Gson();
		CallEstablishReq callEstablishReq = gson.fromJson(reqBody.toString(), CallEstablishReq.class);

		// 判断签名是否正确

		// TODO 业务处理。开发者根据自己的需求实现

		// 响应
		CallEstablishResp callEstablishResp = new CallEstablishResp();
		callEstablishResp.setRespCode(RespCode.SUCCESS);

		String respStr = gson.toJson(callEstablishResp);
		System.out.println("返回的数据:" + respStr);

		return ResponseUtil.json(callEstablishResp, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/hangup", method = RequestMethod.POST)
	public ResponseEntity<Object> hangup(HttpServletRequest request) throws IOException {
		// 获取请求参数
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String str = null;
		StringBuffer reqBody = new StringBuffer();
		while ((str = reader.readLine()) != null) {
			reqBody.append(str);
		}
		System.out.println("收到请求：" + reqBody);

		// 将数据注入对应的bean
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		HangupReq hangupReq = gson.fromJson(reqBody.toString(), HangupReq.class);

		// 判断签名是否正确

		// TODO 通过callId判断是否重复通知

		// TODO 业务处理。开发者根据自己的需求实现

		// 响应
		HangupResp hangupResp = new HangupResp();
		hangupResp.setRespCode(RespCode.SUCCESS);

		String respStr = gson.toJson(hangupResp);
		System.out.println("返回的数据:" + respStr);

		return ResponseUtil.json(hangupResp, HttpStatus.OK);
	}
}
