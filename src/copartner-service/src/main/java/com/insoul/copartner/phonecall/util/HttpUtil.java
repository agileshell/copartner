package com.insoul.copartner.phonecall.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * http请求工具
 */
public class HttpUtil {
	/**
	 * 创建url
	 * 
	 * @param funAndOperate
	 *            请求的功能和操作
	 * @return
	 */
	public static String createUrl(String funAndOperate, String accountSid, String key) {
		// 时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());

		// 签名
		String sig = DigestUtils.md5Hex(accountSid + key + timestamp);

		return Config.BASE_URL + accountSid + "/" + funAndOperate + "sig=" + sig + "&timestamp=" + timestamp;
	}

	/**
	 * 创建请求头
	 * 
	 * @param body
	 * @return
	 */
	public static Map<String, String> createHeaders(String body) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", Config.CONTENT_TYPE);
		headers.put("Accept", Config.ACCEPT);
		try {
			headers.put("Content-Length", String.valueOf(body.getBytes("UTF8").length));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return headers;
	}

	/**
	 * post请求
	 * 
	 * @param funAndOperate
	 *            功能和操作
	 * @param body
	 *            要post的数据
	 * @return
	 * @throws IOException
	 */
	public static String post(String funAndOperate, String body, String accountSid, String accountKey) {
		// 入参校验
		if (funAndOperate == null || body == null)
			return "postWithHeaders校验参数不通过";

		// 构造请求数据
		String url = createUrl(funAndOperate, accountSid, accountKey);
		System.out.println(url);
		Map<String, String> headers = createHeaders(body);

		System.out.println("url:\r\n" + url);
		System.out.println("body:\r\n" + body);
		System.out.println("headers:\r\n" + headers);

		String result = "";
		try {
			OutputStreamWriter out = null;
			BufferedReader in = null;
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// 添加请求头
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				conn.setRequestProperty(key, headers.get(key));
			}

			// 设置连接参数
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(4000);
			conn.setReadTimeout(5000);

			// 提交数据
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(body);
			out.flush();

			// 读取返回数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += "\n";
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 回调测试工具方法
	 * 
	 * @param url
	 * @param reqStr
	 * @return
	 */
	public static String postHuiDiao(String url, String body) {
		// 构造请求数据
		String result = "";
		try {
			OutputStreamWriter out = null;
			BufferedReader in = null;
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// 设置连接参数
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(4000);
			conn.setReadTimeout(5000);

			// 提交数据
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(body);
			out.flush();

			// 读取返回数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += "\n";
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}