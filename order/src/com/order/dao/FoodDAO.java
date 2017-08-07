package com.order.dao;

import java.util.List;

import com.order.entity.Food;

public interface FoodDAO {

	/**
	 * ���ݵ�ǰҳ��ÿҳ��ʾ����������ҳ��ѯ����
	 */
	public List<Food> getFoodByStartPage(Food food,int startIndex,int maxResult);
	
	/**
	 * �ܼ�¼��
	 */
	public Long getFoodCount(Food food);
	
	/**
	 * ���һ����¼
	 */
	public int addFood(Food food);
	
	/**
	 * ����id��ѯһ����¼
	 */
	public Food findFoodById(int id);
	
	/**
	 * ����
	 *
	 */
	
	public int updateFood(Food food);
	
	/**
	 * ����idɾ��һ����¼
	 */
	public int deleteFoodById(int id);
}
