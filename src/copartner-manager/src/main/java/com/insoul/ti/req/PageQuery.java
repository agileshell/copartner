package com.insoul.ti.req;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年3月27日 下午7:15:16
 */
public class PageQuery {

	public static final int DEFAULT_PAGE_SIZE = 10;

	private int page_size;

	private int page;

	private int pre_page;

	private int next_page;
	
	private long index;
	
	private String queryString;

	public PageQuery(int page) {
		this(DEFAULT_PAGE_SIZE, page);
	}

	public PageQuery(int page_size, int page) {
		super();
		this.page_size = page_size;
		if (page <= 1) {
			pre_page = 1;
			this.page = 1;
			next_page = 2;
		} else {
			pre_page = page - 1;
			this.page = page;
			next_page = page + 1;
		}
		index = Long.valueOf((this.page - 1) * this.page_size);
	}
	
	public PageQuery reset() {
		pre_page = page - 1;
		next_page = page;
		return this;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPre_page() {
		return pre_page;
	}

	public void setPre_page(int pre_page) {
		this.pre_page = pre_page;
	}

	public int getNext_page() {
		return next_page;
	}

	public void setNext_page(int next_page) {
		this.next_page = next_page;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
}