package com.insoul.copartner.phonecall.model;

/**
 * 呼叫鉴权请求参数
 */
public class CallAuthReq {
	// 参数详述请参考http://www.qingmayun.com/document.html
	private String accountId;
	private String appId;
	private String clientNumber;
	private String callType;
	private String caller;
	private String called;
	private String fromSerNum;
	private String toSerNum;
	private String callId;

	private String timestamp;
	private String sig;

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

	public String getFromSerNum() {
		return fromSerNum;
	}

	public void setFromSerNum(String fromSerNum) {
		this.fromSerNum = fromSerNum;
	}

	public String getToSerNum() {
		return toSerNum;
	}

	public void setToSerNum(String toSerNum) {
		this.toSerNum = toSerNum;
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

	@Override
	public String toString() {
		return "CallAuthReq [accountId=" + accountId + ", appId=" + appId + ", clientNumber=" + clientNumber
				+ ", callType=" + callType + ", caller=" + caller + ", called=" + called + ", fromSerNum=" + fromSerNum
				+ ", toSerNum=" + toSerNum + ", callId=" + callId + ", timestamp=" + timestamp + ", sig=" + sig + "]";
	}

}
