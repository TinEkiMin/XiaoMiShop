package com.edu.seiryo.util;

import java.util.List;

public class PageHelper {

	private int row=5;//每页行数

	private int total;//总行数（查询）

	private int currentPage;//当前第几页

	private int totalPage;//总页数(计算)

	private List list;//查询的数据（查询）

	/**
	 * @param row
	 * @param total
	 * @param currentPage
	 * @param totalPage
	 * @param list
	 */
	public PageHelper(int row, int total, int currentPage, List list) {
		super();
		this.row = row;
		this.total = total;
		this.currentPage = currentPage;
	
		if (total % row == 0) {
			this.totalPage = total / row;
		} else {
			this.totalPage = total / row + 1;
		}
		this.list = list;
	}

	/**
	 * 
	 */
	public PageHelper() {
		super();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
