package com.order.entity;

import java.util.Date;
/**
 * ����
 * @author ApnLee
 *
 */
public class DinnerTable {
	//�������
	private int id;
	//������
	private String tableName;
	//����״̬
	private int tableStatus;
	//Ԥ��ʱ��
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
