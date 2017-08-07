package com.order.entity;

import java.util.Date;
/**
 * ²Í×À
 * @author ApnLee
 *
 */
public class DinnerTable {
	//²Í×À±àºÅ
	private int id;
	//²Í×ÀÃû
	private String tableName;
	//²Í×À×´Ì¬
	private int tableStatus;
	//Ô¤¶¨Ê±¼ä
	private Date orderDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
