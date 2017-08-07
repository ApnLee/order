package com.order.dao;

import java.util.List;

import com.order.entity.OrderDetail;

public interface OrderDetailDAO {

	public void add(OrderDetail orderDetail);
	
	public List<OrderDetail> query(int orderId);
	
	public List<OrderDetail> findOrderById(int id);
}
