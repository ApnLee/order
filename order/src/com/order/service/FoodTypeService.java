package com.order.service;

import java.util.List;

import com.order.entity.DinnerTable;
import com.order.entity.FoodType;

/**
 * 菜系的管理业务层
 * @author ApnLee 
 *
 */
public interface FoodTypeService {
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
