package com.order.service;

import java.util.List;

import com.order.entity.DinnerTable;

public interface DinnerTableService {
	/**
	 * ���һ����¼
	 */
	public int addDinnerTable(DinnerTable table);
	
	/**
	 * ����������ѯ���еĲ���
	 */
	public List<DinnerTable> getAllDinnerTable(DinnerTable table);
	
	/**
	 * ����id����һ����¼
	 */
	public int updateStatus(DinnerTable table);
	
	/**
	 * ����id��ѯһ����¼
	 */
	public DinnerTable findTableById(int id);
	
	/**
	 * ����idɾ��һ����¼
	 */
	public int deleteTableById(int id);
}
