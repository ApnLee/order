package com.order.service.impl;

import java.util.Date;
import java.util.List;

import com.order.dao.DinnerTableDAO;
import com.order.dao.impl.DinnerTableDAOImpl;
import com.order.entity.DinnerTable;
import com.order.service.DinnerTableService;

public class DinnerTableServiceImpl implements DinnerTableService {

	DinnerTableDAO tableDao=new DinnerTableDAOImpl();
	public int addDinnerTable(DinnerTable table) {
		
		return tableDao.addDinnerTable(table);
	}

	public List<DinnerTable> getAllDinnerTable(DinnerTable table) {
		
		return tableDao.getAllDinnerTable(table);
	}

	public int updateStatus(DinnerTable table) {
		if (table.getTableStatus()==0) {//空闲
			table.setTableStatus(1);
			table.setOrderDate(new Date());
		}else{
			table.setTableStatus(0);
			//清空当前时间
			table.setOrderDate(null);
		}
		return tableDao.updateStatus(table);
	}

	public DinnerTable findTableById(int id) {
		
		return tableDao.findTableById(id);
	}

	public int deleteTableById(int id) {
		
		return tableDao.deleteTableById(id);
	}
	

}
