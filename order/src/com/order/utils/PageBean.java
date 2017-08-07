package com.order.utils;

import java.util.List;

/**
 * ��ҳ��ͨ����
 * @author ApnLee
 *
 * @param <T>
 */
public class PageBean<T> {

	//��ǰҳ
	int currentPage=1;
	//��ҳ��
	int totalPage;
	//ÿҳ��ʾ����
	int maxResult=10;
	//��ҳ������
	List<T> datas;
	//�ܼ�¼��
	int totalCount;
	
	//����·��
	String url;
	

	public int getTotalPage(){
		//�����ܼ�¼���������ҳ��
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
