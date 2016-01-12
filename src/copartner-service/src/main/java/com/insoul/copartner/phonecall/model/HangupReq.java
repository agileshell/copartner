package com.insoul.copartner.phonecall.model;

import java.util.Date;

/**
 * 呼叫挂断请求参数
 */
public class HangupReq {
	// 参数详述请参考http://www.qingmayun.com/document.html
	private String accountId;
	private String appId;
	private String clientNumber;
	private String callType;
	private String caller;
	private String called;
	private Date startTime;
	private Date stopTime;
	private String byeType;
	private String callId;
	private String timestamp;
	private String sig;

	private String callTime;
	private String recordurl;
	private String reason;

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getRecordurl() {
		return recordurl;
	}

	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getByeType() {
		return byeType;
	}

	public void setByeType(String byeType) {
		this.byeType = byeType;
	}

	@Override
	public String toString() {
		return "HangupReq [accountId=" + accountId + ", appId=" + appId + ", clientNumber=" + clientNumber
				+ ", callType=" + callType + ", caller=" + caller + ", called=" + called + ", startTime=" + startTime
				+ ", stopTime=" + stopTime + ", byeType=" + byeType + ", callId=" + callId + ", timestamp=" + timestamp
				+ ", sig=" + sig + ", callTime=" + callTime + ", recordurl=" + recordurl + ", reason=" + reason + "]";
	}

}
