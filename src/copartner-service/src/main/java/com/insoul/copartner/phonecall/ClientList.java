package com.insoul.copartner.phonecall;

import com.insoul.copartner.phonecall.util.Config;
import com.insoul.copartner.phonecall.util.HttpUtil;

/**
 * 查询client账号接口调用示例
 */
public class ClientList {
	/**
	 * url中{function}/{operation}?部分
	 */
	private static String funAndOperate = "clientList?";

	// 参数详述请参考http://www.qingmayun.com/document.html
	private static int start = 0;// 开始的序号，默认从0开始
	private static int limit = 100;// 一次查询的最大条数，最小是1条，最大是100条
	private static String mobile = "";// 绑定的手机号码
	private static String clientNumber = "";// 子账号ID

	/**
	 * 查询client账号
	 */
	public static void main(String args[]) {
		// 生成body
		String body = null;
		if (Config.CONTENT_TYPE.equals("application/json")) {
			body = "{\"client\":{\"appId\":\"" + Config.APP_ID + "\",\"start\":\"" + start + "\",\"limit\":\"" + limit
					+ "\",\"mobile\":\"" + mobile + "\",\"clientNumber\":\"" + clientNumber + "\"}}";
		}

		// 提交请求
		String result = HttpUtil.post(funAndOperate, body, Config.ACCOUNT_SID, Config.KEY);
		System.out.println("result:" + result);
	}
}
