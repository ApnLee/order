package com.order.dao;

import java.util.List;

import com.order.entity.DinnerTable;
import com.order.entity.FoodType;

/**
 * 餐桌的管理DAO
 * @author ApnLee 
 *
 */
public interface FoodTypeDAO {
	/**
	 * 添加一条记录
	 */
	public int addFoodType(FoodType type);
	
	/**
	 * 根据条件查询所有的餐桌
	 */
	public List<FoodType> getAllFoodType(FoodType type);
	
	/**
	 * 根据id更新一条记录
	 */
	public int updateType(FoodType type);

	
	/**
	 * 根据id查询一条记录
	 */
	public FoodType findTypeById(int id);
	
	/**
	 * 根据id删除一条记录
	 */
	public int deleteTypeById(int id);
}
