package com.order.entity;

import java.util.Date;

public class Order {

	private int id;
	private int tableId;
	private Date orderDate;
	private double totalPrice;
	private int orderStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {

		return "Order [id=" + id + ",table_id=" + tableId + ",orderDate="
				+ orderDate +",totalPrice=" + totalPrice + ",orderStatus="
				+ orderStatus +"]";
	}

	
}
