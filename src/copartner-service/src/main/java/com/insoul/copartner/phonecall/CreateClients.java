package com.insoul.copartner.phonecall;

import com.insoul.copartner.phonecall.util.Config;
import com.insoul.copartner.phonecall.util.HttpUtil;

/**
 * 创建client账号接口调用示例
 */
public class CreateClients {
	/**
	 * url中{function}/{operation}?部分
	 */
	private static String funAndOperate = "clients?";

	// 参数详述请参考http://www.qingmayun.com/document.html
	private static String friendlyName = "15821512816";// Client名称。可由英文字母和阿拉伯数字组成Client名称，同一个应用下唯一
	private static String mobile = "15821512816";// 绑定的手机号码（回拨时必填,同一个应用内每个手机号只能绑定一个子账号）
	private static double balance = 2.2;

	/**
	 * 创建client账号
	 */
	public static void main(String args[]) {
		// 生成body
		String body = null;
		if (Config.CONTENT_TYPE.equals("application/json")) {
			body = "{\"client\": {\"appId\":\"" + Config.APP_ID + "\",\"friendlyName\":\"" + friendlyName
					+ "\",\"mobile\":\"" + mobile + "\",\"balance\":\"" + balance + "\"}}";
		}

		// 提交请求
		String result = HttpUtil.post(funAndOperate, body, Config.ACCOUNT_SID, Config.KEY);
		System.out.println("result:" + result);
	}
}
