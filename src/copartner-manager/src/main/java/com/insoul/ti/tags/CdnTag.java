package com.insoul.ti.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年8月3日 下午2:12:24
 */
public class CdnTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String domain;
	
	private String path;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
		    if (StringUtils.startsWith(path, "http://")) {
		        out.write(path);
		    } else {
		        out.write(domain + path);
		    }
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}