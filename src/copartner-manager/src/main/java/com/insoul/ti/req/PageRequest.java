package com.insoul.ti.req;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public abstract class PageRequest {
	
	public static final int DEFAULT_PAGE_LIMIT = 20;
	
	private PageQuery query;

	private int page = 1;

	private int limit = DEFAULT_PAGE_LIMIT;
	
	public PageRequest init() {
		query = new PageQuery(limit, page);
		Q();
		return this;
	}
	
	protected abstract PageRequest Q();
	
	public PageQuery getQuery() {
		return query;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}