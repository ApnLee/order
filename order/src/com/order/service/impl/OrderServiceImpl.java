package com.order.service.impl;

import java.util.Date;
import java.util.List;

import com.order.dao.OrderDAO;
import com.order.dao.impl.OrderDAOImpl;
import com.order.entity.Order;
import com.order.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDAO orderDao = new OrderDAOImpl();

	public Order findOrderById(int id) {
		
		return orderDao.findOrderById(id);
	}

	public void updateStatus(Order order) {
		orderDao.updateStatus(order);
		
	}

	public List<Order> query(Order order) {
		
		return orderDao.query(order);
	}

	public Long addOrder(Order order) {
		
		return orderDao.addOrder(order);
	}

	public int getCount() {
		
		return orderDao.getCount();
	}

	public int getMaxId() {
		
		return orderDao.getMaxId();
	}
	

}
