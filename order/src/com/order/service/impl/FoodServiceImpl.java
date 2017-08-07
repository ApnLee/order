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
	
		//�õ�Ա�����ܼ�¼��
		int totalCount=foodDao.getFoodCount(food).intValue();
		
		//���Ƹ��ҵ�pageBean
		pageBean.setTotalCount(totalCount);
		
		//��ǰҳ���ܴ�����ҳ��
		if(pageBean.getCurrentPage() > pageBean.getTotalPage()){
			pageBean.setCurrentPage(pageBean.getTotalPage());
		}
		
		//��ǰҳ����С��1
		if (pageBean.getCurrentPage() < 1 ) {
			pageBean.setCurrentPage(1);
		}
		
		//�����ʼ������
		int startIndex=((pageBean.getCurrentPage()-1)*pageBean.getMaxResult());
		
		//�õ���ҳ������
		List<Food> emps=foodDao.getFoodByStartPage(food, startIndex, pageBean.getMaxResult());
		
		//��װ��pageBean
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
