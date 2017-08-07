package com.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.order.dao.OrderDAO;
import com.order.entity.DinnerTable;
import com.order.entity.Food;
import com.order.entity.Order;
import com.order.utils.ComPoolUtil;

public class OrderDAOImpl implements OrderDAO {

	private QueryRunner qr = ComPoolUtil.getQueryRunner();
	
	public Long addOrder(Order order) {
		Long id = null;
		String sql = "insert into orders (table_id,orderDate,totalPrice) values (?,?,?)";		
		try {
				qr.update(sql,order.getTableId(),order.getOrderDate(),order.getTotalPrice());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
				
			return id;
	}
	
	public int getCount(){
		String sql = "select count(*) from orders";
		
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateStatus(Order order) {
		String sql = "update orders set orderStatus = ? where id = ?";
				
		try {
				qr.update(sql, order.getOrderStatus(),order.getId());
		} catch (SQLException e) {
				throw new RuntimeException(e);
		}

	}

	public List<Order> query(Order order) {
		String sql = "select * from orders";

		try {
			return qr.query(sql, new BeanListHandler<Order>(Order.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public int getMaxId(){
		String sql = "select max(id) from orders";
		
		try {
			Integer count = qr.query(sql, new ScalarHandler<Integer>());
			return count.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Order findOrderById(int id) {
		String sql = "select count(*) from orders";
		try {
			return qr.query(sql,new BeanHandler<Order>(Order.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);		
		}

	}

}
