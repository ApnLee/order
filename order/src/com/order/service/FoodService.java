package com.order.service;

import com.order.entity.Food;
import com.order.utils.PageBean;

public interface FoodService {
	
	/**
	 * 封装分页的信息
	 */
	public void setPageBean(PageBean<Food> pageBean,Food food);
	
	/**
	 * 添加一条记录
	 * @param food 
	 * @return 返回受影响的行
	 */
	public int addFood(Food food);
	
	/**
	 * 根据ID查找一条记录
	 */
	public Food findFoodById(int id);	
	
	/**
	 * 跟新一条记录
	 */
	public int updateFood(Food food);	
	
	/**
	 * 根据id删除一条记录
	 */
	public int deleteFoodById(int id);
}
