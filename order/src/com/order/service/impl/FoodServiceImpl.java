package com.order.service.impl;



import java.util.List;

import com.order.dao.FoodDAO;
import com.order.dao.impl.FoodDAOImpl;
import com.order.entity.Food;
import com.order.service.FoodService;
import com.order.utils.PageBean;

public class FoodServiceImpl implements FoodService {
	FoodDAO foodDao=new FoodDAOImpl();
	public void setPageBean(PageBean<Food> pageBean, Food food) {
	
		//得到员工的总记录数
		int totalCount=foodDao.getFoodCount(food).intValue();
		
		//复制给我的pageBean
		pageBean.setTotalCount(totalCount);
		
		//当前页不能大于总页数
		if(pageBean.getCurrentPage() > pageBean.getTotalPage()){
			pageBean.setCurrentPage(pageBean.getTotalPage());
		}
		
		//当前页不能小于1
		if (pageBean.getCurrentPage() < 1 ) {
			pageBean.setCurrentPage(1);
		}
		
		//算出开始的条数
		int startIndex=((pageBean.getCurrentPage()-1)*pageBean.getMaxResult());
		
		//得到分页的数据
		List<Food> emps=foodDao.getFoodByStartPage(food, startIndex, pageBean.getMaxResult());
		
		//封装到pageBean
		pageBean.setDatas(emps);
	}
	public int addFood(Food food) {
		
		return foodDao.addFood(food);
	}
	public Food findFoodById(int id) {
		
		return foodDao.findFoodById(id);
	}
	public int updateFood(Food food) {
		
		return foodDao.updateFood(food);
	}
	public int deleteFoodById(int id) {
		
		return foodDao.deleteFoodById(id);
	}

}
