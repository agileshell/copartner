package com.insoul.copartner.phonecall;

import com.insoul.copartner.phonecall.util.Config;
import com.insoul.copartner.phonecall.util.HttpUtil;

/**
 * 关闭Client帐号接口调用示例
 */
public class CloseClient {
	/**
	 * url中{function}/{operation}?部分
	 */
	private static String funAndOperate = "closeClient?";

	// 参数详述请参考http://www.qingmayun.com/document.html
	private static String subAcctId = "66607052138662";// 应用子账号ID

	/**
	 * 关闭Client帐号
	 */
	public static void main(String args[]) {

		String body = null;
		if (Config.CONTENT_TYPE.equals("application/json")) {
			body = "{ \"clientInfo\" : { \"appId\" : \"" + Config.APP_ID + "\", \"subAcctId\" : \"" + subAcctId
					+ "\"}}";
		}

		// 提交请求
		String result = HttpUtil.post(funAndOperate, body, Config.ACCOUNT_SID, Config.KEY);
		System.out.println("result:" + result);
	}
}
