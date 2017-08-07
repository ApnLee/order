package com.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.order.dao.DinnerTableDAO;
import com.order.dao.FoodTypeDAO;
import com.order.entity.DinnerTable;
import com.order.entity.FoodType;
import com.order.utils.ComPoolUtil;

public class FoodTypeDAOImpl implements FoodTypeDAO {
	/**
	 * ���
	 */
	public int addFoodType(FoodType type) {
		//��Ӱ�����
		int count=0;
		
		try {
			count=ComPoolUtil.getQueryRunner().update("insert into foodtype (typeName) value(?)", type.getTypeName());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * ��ѯ
	 */
	public List<FoodType> getAllFoodType(FoodType type) {
		List<FoodType> types=null;
		StringBuilder sb=new StringBuilder("select * from foodtype");
		//����ϵ�����Ʋ���
		if(type.getTypeName()!=null && !"".equals(type.getTypeName())){
			sb.append(" and typeName like '%"+type.getTypeName()+"%'");
		}
		if(type.getId() != 0){
			sb.append(" and id = "+type.getId());
		}
		//�ѵ�һ��and�滻��where
		String sql=sb.toString().replaceFirst("and", "where");
		try {
			types=ComPoolUtil.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return types;
	}
	
	/**
	 * ����
	 */
	public int updateType(FoodType type) {
			int count=0;
			
			try {
				count=ComPoolUtil.getQueryRunner().update("update foodtype set typeName = ? where id = ? ", type.getTypeName(),type.getId());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			return count;
		
	}

	/**
	 * ����
	 */
	public FoodType findTypeById(int id) {
		FoodType type=null;
		try {
			type=ComPoolUtil.getQueryRunner().query("select * from foodtype where id = ?",new BeanHandler<FoodType>(FoodType.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return type;
	}

	/**
	 * ɾ��
	 */
	public int deleteTypeById(int id) {
		int count=0;
		try {
			count=ComPoolUtil.getQueryRunner().update("delete from foodtype where id = ?",id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;
	}


	
}
