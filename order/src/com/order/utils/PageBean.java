package com.order.utils;

import java.util.List;

/**
 * 分页的通用类
 * @author ApnLee
 *
 * @param <T>
 */
public class PageBean<T> {

	//当前页
	int currentPage=1;
	//总页数
	int totalPage;
	//每页显示多少
	int maxResult=10;
	//分页的数据
	List<T> datas;
	//总记录数
	int totalCount;
	
	//访问路径
	String url;
	

	public int getTotalPage(){
		//根据总记录数计算出总页数
		return this.getTotalCount()%this.maxResult == 0 ? this.getTotalCount()/this.maxResult : this.getTotalCount()/this.maxResult+1;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getMaxResult() {
		return maxResult;
	}


	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}


	public List<T> getDatas() {
		return datas;
	}


	public void setDatas(List<T> datas) {
		this.datas = datas;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

}
