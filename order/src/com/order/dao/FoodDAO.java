package com.order.dao;

import java.util.List;

import com.order.entity.Food;

public interface FoodDAO {

	/**
	 * 根据当前页和每页显示的条数，分页查询数据
	 */
	public List<Food> getFoodByStartPage(Food food,int startIndex,int maxResult);
	
	/**
	 * 总记录数
	 */
	public Long getFoodCount(Food food);
	
	/**
	 * 添加一条记录
	 */
	public int addFood(Food food);
	
	/**
	 * 根据id查询一条记录
	 */
	public Food findFoodById(int id);
	
	/**
	 * 更新
	 *
	 */
	
	public int updateFood(Food food);
	
	/**
	 * 根据id删除一条记录
	 */
	public int deleteFoodById(int id);
}
