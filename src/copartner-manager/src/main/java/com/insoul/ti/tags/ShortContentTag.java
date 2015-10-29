package com.insoul.ti.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringEscapeUtils;

import com.insoul.ti.utils.Utils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年8月3日 下午2:12:24
 */
public class ShortContentTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String content;
	
	private int length;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write(StringEscapeUtils.escapeHtml4(Utils.getShort(getContent(), getLength())));
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}