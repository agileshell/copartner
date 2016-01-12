package com.insoul.copartner.phonecall.model;

/**
 * 呼叫鉴权响应参数
 */
public class CallAuthResp {
	// 参数详述请参考http://www.qingmayun.com/document.html
	private String respCode;
	private String fromSerNum;
	private String toSerNum;
	private String allowedCallTime;
	private String callingAudioId;
	private String connectAudioId;
	private String connectCalledAudioId;
	private String connectTimeoutAudioId;
	private String lackOftimeAudioId;
	private String noBalanceTime;
	private String record;
	private String reason;

	public String getNoBalanceTime() {
		return noBalanceTime;
	}

	public void setNoBalanceTime(String noBalanceTime) {
		this.noBalanceTime = noBalanceTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
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

	public String getAllowedCallTime() {
		return allowedCallTime;
	}

	public void setAllowedCallTime(String allowedCallTime) {
		this.allowedCallTime = allowedCallTime;
	}

	public String getCallingAudioId() {
		return callingAudioId;
	}

	public void setCallingAudioId(String callingAudioId) {
		this.callingAudioId = callingAudioId;
	}

	public String getConnectAudioId() {
		return connectAudioId;
	}

	public void setConnectAudioId(String connectAudioId) {
		this.connectAudioId = connectAudioId;
	}

	public String getConnectCalledAudioId() {
		return connectCalledAudioId;
	}

	public void setConnectCalledAudioId(String connectCalledAudioId) {
		this.connectCalledAudioId = connectCalledAudioId;
	}

	public String getConnectTimeoutAudioId() {
		return connectTimeoutAudioId;
	}

	public void setConnectTimeoutAudioId(String connectTimeoutAudioId) {
		this.connectTimeoutAudioId = connectTimeoutAudioId;
	}

	public String getLackOftimeAudioId() {
		return lackOftimeAudioId;
	}

	public void setLackOftimeAudioId(String lackOftimeAudioId) {
		this.lackOftimeAudioId = lackOftimeAudioId;
	}

}
