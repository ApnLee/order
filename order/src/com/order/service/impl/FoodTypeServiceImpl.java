package com.order.service.impl;

import java.util.List;

import com.order.dao.FoodTypeDAO;
import com.order.dao.impl.FoodTypeDAOImpl;
import com.order.entity.FoodType;
import com.order.service.FoodTypeService;

public class FoodTypeServiceImpl implements FoodTypeService {
	
	FoodTypeDAO typeDao=new FoodTypeDAOImpl();
	
	public int addFoodType(FoodType type) {
		
		return typeDao.addFoodType(type);
	}

	public List<FoodType> getAllFoodType(FoodType type) {
		
		return typeDao.getAllFoodType(type);
	}

	public int updateType(FoodType type) {
				
		return typeDao.updateType(type);
	}

	public FoodType findTypeById(int id) {
		
		return typeDao.findTypeById(id);
	}

	public int deleteTypeById(int id) {
		
		return typeDao.deleteTypeById(id);
	}

}
