package com.order.service;

import com.order.entity.Food;
import com.order.utils.PageBean;

public interface FoodService {
	
	/**
	 * ��װ��ҳ����Ϣ
	 */
	public void setPageBean(PageBean<Food> pageBean,Food food);
	
	/**
	 * ���һ����¼
	 * @param food 
	 * @return ������Ӱ�����
	 */
	public int addFood(Food food);
	
	/**
	 * ����ID����һ����¼
	 */
	public Food findFoodById(int id);	
	
	/**
	 * ����һ����¼
	 */
	public int updateFood(Food food);	
	
	/**
	 * ����idɾ��һ����¼
	 */
	public int deleteFoodById(int id);
}
