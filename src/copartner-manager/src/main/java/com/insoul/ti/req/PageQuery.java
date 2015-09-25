package com.insoul.ti.req;

import org.apache.commons.lang3.StringUtils;

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
	
	private int index;
	
	private String queryString;
	
	private int count;

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
		index = (this.page - 1) * this.page_size;
	}
	
	public boolean isLastPage() {
	    return (count % page_size == 0 ? (count / page_size) : ((count / page_size) + 1)) == page;
	}
	
	public int getLastPageNum() {
	    return count % page_size == 0 ? (count / page_size) : ((count / page_size) + 1);
	}
	
	public boolean isHomePage() {
	    return page == 1;
	}
	
	public boolean isPage_1() {
	    int lastPage = getLastPageNum();
	    return page + 1 < lastPage;
	}
	
	public int getPage_1Num() {
	    return page + 1;
	}
    
    public boolean isPage_2() {
        int lastPage = getLastPageNum();
        return page + 2 < lastPage;
    }
    
    public int getPage_2Num() {
        return page + 2;
    }
    
    public boolean isPage_3() {
        int lastPage = getLastPageNum();
        return page + 3 < lastPage;
    }
    
    public int getPage_3Num() {
        return page + 3;
    }
	
	public PageQuery reset() {
		pre_page = page - 1;
		next_page = page;
		return this;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
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
		return "&limit=" + this.page_size + (StringUtils.isBlank(queryString) ? "" : "&") + StringUtils.defaultString(queryString, StringUtils.EMPTY);
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}