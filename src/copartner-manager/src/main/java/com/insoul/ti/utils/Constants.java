package com.insoul.ti.utils;

import java.nio.charset.Charset;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月5日 下午11:02:00
 */
public interface Constants {
    
    String DEFAULT_ADMIN_PASSWORD_SALT = "68742f";
	
	String MAIL_SPLIT = ";";
	
	int DEFAULT_PAGE_SIZE = 10;
	
	int MAX_PAGE_SIZE = 100;
	
	String PROPERTIES_SPLIT = ";";
	
	String EQ_SPLIT = ":";
	
	String DOT_SPLIT = ",";
	
	String UTF_8_VALUE = "UTF-8";
	
	String ISO_8859_1_VALUE = "iso-8859-1";
	
	String GBK_VALUE = "GBK";
	
	String GB18030_VALUE = "gb18030";
	
	String GB2312_VALUE = "gb2312";
	
	Charset UTF_8 = Charset.forName(Constants.UTF_8_VALUE);
	
	Charset ISO_8859_1 = Charset.forName(Constants.ISO_8859_1_VALUE);
	
	Charset GBK = Charset.forName(Constants.GBK_VALUE);
	
	Charset GB18030 = Charset.forName(Constants.GB18030_VALUE);
	
	Charset GB2312 = Charset.forName(Constants.GB2312_VALUE);

	byte[] UTF8_BOM_BYTE = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
}