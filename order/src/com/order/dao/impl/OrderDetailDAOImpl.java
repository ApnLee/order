package com.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.order.dao.OrderDetailDAO;
import com.order.entity.OrderDetail;
import com.order.utils.ComPoolUtil;

public class OrderDetailDAOImpl implements OrderDetailDAO {

	private QueryRunner qr = ComPoolUtil.getQueryRunner();
	
	public void add(OrderDetail orderDetail) {
		String sql = " insert into orderdetail(orderId,food_id,foodCount) value(?,?,?)";
		
		try {
			qr.update(sql, orderDetail.getOrderId(),orderDetail.getFood_id(),orderDetail.getFoodCount());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<OrderDetail> query(int orderId) {
		String sql = "select a.*,b.foodName,b.price from orderdetail a,food b where a.food_id=b.id and a.orderId=?";
		try {
			return qr.query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class),orderId);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public List<OrderDetail> findOrderById(int id) {
		String sql = "select * from orderdetail where orderId = ?";
		try {
			return qr.query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
