package com.eatApp.service.impl;

import java.util.List;

import com.eatApp.dao.BackOrderDAO;
import com.eatApp.dao.impl.BackOrderDAOImpl;
import com.eatApp.entity.Order;
import com.eatApp.entity.Snack;
import com.eatApp.service.BackOrderService;
import com.eatApp.utils.PageBean;

public class BackOrderServiceImpl implements BackOrderService {
	
	BackOrderDAO backOrderDao = new BackOrderDAOImpl();

	@Override
	public void setPageBean(PageBean<Order> pageBean, Order order) {
		
		System.out.println("current:"+pageBean.getCurrent());
		// 得到零食总记录数
		int totalCount = backOrderDao.getOrderCount(order).intValue();
		
		// 赋值给我的PageBean
		pageBean.setTotalCount(totalCount);
		System.out.println("total:"+pageBean.getTotal());
		
		// 当前页不能大于总页数
		if (pageBean.getCurrent() > pageBean.getTotal()) {
			pageBean.setCurrent(pageBean.getTotal());
		}
		System.out.println("current2:"+pageBean.getCurrent());
		
		// 当前页不能小于1
		if (pageBean.getCurrent() < 1) {
			pageBean.setCurrent(1);
		}
		
		//算出开始的条数
		System.out.println("current3:"+pageBean.getCurrent());
		int startIndex = ((pageBean.getCurrent()-1)*pageBean.getMaxResult());
	    System.out.println("startindex:"+startIndex);
	    
		//得到分页的数据
		List<Order> datas = backOrderDao.findAllOrder(order, startIndex, pageBean.getMaxResult());
		
		//封装到Pagebean
		pageBean.setDatas(datas);
		
	}

	@Override
	public Order findOrderById(int id) {
		
		return backOrderDao.findOrderById(id);
	}

	@Override
	public int update(Order order) {
		
		return backOrderDao.update(order);
	}

	@Override
	public int deleteOrder(int id) {
		
		return backOrderDao.deleteOrder(id);
	}

	

}
