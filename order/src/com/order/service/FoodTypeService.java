package com.order.service;

import java.util.List;

import com.order.entity.DinnerTable;
import com.order.entity.FoodType;

/**
 * ��ϵ�Ĺ���ҵ���
 * @author ApnLee 
 *
 */
public interface FoodTypeService {
	/**
	 * ���һ����¼
	 */
	public int addFoodType(FoodType type);
	
	/**
	 * ����������ѯ���еĲ���
	 */
	public List<FoodType> getAllFoodType(FoodType type);
	
	/**
	 * ����id����һ����¼
	 */
	public int updateType(FoodType type);
	
	/**
	 * ����id��ѯһ����¼
	 */
	public FoodType findTypeById(int id);
	
	/**
	 * ����idɾ��һ����¼
	 */
	public int deleteTypeById(int id);
}
