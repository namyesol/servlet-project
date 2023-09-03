package com.common;

/**
 * Value Object for Pagination Request
 */
public class Page {
	
	public static final int DEFAULT_PAGE_SIZE = 5;
	public static final int START_PAGE_OFFSET = 1;
	
	private int page;
	private int size;
	
	public Page(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", size=" + size + "]";
	}
	
}
