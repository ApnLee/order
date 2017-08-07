package com.order.service;

import java.util.List;

import com.order.entity.Order;

public interface OrderService {


	/**
	 * 根据id查询一条记录
	 */
	public Order findOrderById(int id);

	/**
	 * 根据id更新一条记录
	 */
	public void updateStatus(Order order);
	
	/**
	 * 查询
	 */
	public List<Order> query(Order order);
	
	/**
	 * 添加一条记录
	 */
	public Long addOrder(Order order);
	
	public int getCount();
	
	public int getMaxId();
	
}
