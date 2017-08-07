package com.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.order.dao.DinnerTableDAO;
import com.order.entity.DinnerTable;
import com.order.utils.ComPoolUtil;

public class DinnerTableDAOImpl implements DinnerTableDAO {

	public int addDinnerTable(DinnerTable table) {
		//受影响的行
		int count=0;
		
		try {
			count=ComPoolUtil.getQueryRunner().update("insert into dinnerTable (tableName) value (?)", table.getTableName());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return count;

	}

	public List<DinnerTable> getAllDinnerTable(DinnerTable table) {
		List<DinnerTable> tables=null;
		StringBuilder sb=new StringBuilder("select * from dinnertable");
		//按餐桌的名称查找
		if (table.getTableName()!=null && !"".equals(table.getTableName())) {
			sb.append(" and tableName like '%"+table.getTableName()+"%'");
		}
		//按id查找
		if (table.getId()!=0 ) {
			sb.append(" and id = "+table.getId());
		}
		
		//把第一个and替换成where
		String sql=sb.toString().replaceFirst("and", "where");
		
		try {
			tables=ComPoolUtil.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return tables;
	}
	
	public int updateStatus(DinnerTable table) {
		//受影响的行
		int count=0;
		
		try {
			count=ComPoolUtil.getQueryRunner().update("update dinnertable set tableStatus = ?,orderDate = ? where id = ?", 
					table.getTableStatus(),table.getOrderDate(),table.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return count;

	}

	public DinnerTable findTableById(int id) {
		DinnerTable table=null;
		try {
			table =ComPoolUtil.getQueryRunner().query("select * from dinnertable where id = ?", 
					new BeanHandler<DinnerTable>(DinnerTable.class),
					id);
					
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return table;
	}

	public int deleteTableById(int id) {
		int count=0;
		try {
				count =ComPoolUtil.getQueryRunner().update("delete from dinnertable where id = ?", 
					id);
					
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return count;
	}

}
