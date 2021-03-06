package com.order.service;

import java.util.List;

import com.order.entity.DinnerTable;

public interface DinnerTableService {
	/**
	 * 添加一条记录
	 */
	public int addDinnerTable(DinnerTable table);
	
	/**
	 * 根据条件查询所有的餐桌
	 */
	public List<DinnerTable> getAllDinnerTable(DinnerTable table);
	
	/**
	 * 根据id更新一条记录
	 */
	public int updateStatus(DinnerTable table);
	
	/**
	 * 根据id查询一条记录
	 */
	public DinnerTable findTableById(int id);
	
	/**
	 * 根据id删除一条记录
	 */
	public int deleteTableById(int id);
}
