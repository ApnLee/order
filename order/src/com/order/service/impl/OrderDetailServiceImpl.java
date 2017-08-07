package com.order.service.impl;

import java.util.List;

import com.order.dao.OrderDetailDAO;
import com.order.dao.impl.OrderDetailDAOImpl;
import com.order.entity.OrderDetail;
import com.order.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {

	OrderDetailDAO odDao = new OrderDetailDAOImpl();
	
	public void add(OrderDetail orderDetail) {
		odDao.add(orderDetail);

	}

	public List<OrderDetail> query(int orderId) {
		
		return odDao.query(orderId);
	}

	public List<OrderDetail> findOrderById(int id) {
		
		return odDao.findOrderById(id);
	}

}
