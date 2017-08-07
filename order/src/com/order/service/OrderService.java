package com.order.service;

import java.util.List;

import com.order.entity.Order;

public interface OrderService {


	/**
	 * ����id��ѯһ����¼
	 */
	public Order findOrderById(int id);

	/**
	 * ����id����һ����¼
	 */
	public void updateStatus(Order order);
	
	/**
	 * ��ѯ
	 */
	public List<Order> query(Order order);
	
	/**
	 * ���һ����¼
	 */
	public Long addOrder(Order order);
	
	public int getCount();
	
	public int getMaxId();
	
}
