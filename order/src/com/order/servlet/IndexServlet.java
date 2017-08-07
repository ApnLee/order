package com.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.entity.DinnerTable;
import com.order.entity.Food;
import com.order.entity.FoodType;
import com.order.utils.PageBean;

public class IndexServlet extends BaseServlet {

	
	public Object index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  //������в���  
		  this.getServletContext().setAttribute("tables", tableService.getAllDinnerTable(new DinnerTable()));

		  return request.getRequestDispatcher("/app/index.jsp");
		
	}

	public Object menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ѯ���в�ϵ
		List<FoodType> types=typeService.getAllFoodType(new FoodType());				
		//����������
		request.setAttribute("types", types);
		
				//��ȡҳ�洫�����Ĳ�ѯ����
				String foodName=request.getParameter("foodName");//����
				String foodType=request.getParameter("foodType");//����
				
				//��װ����
				Food food = new Food();
				if(foodName!=null && !"".equals(foodName)){
					food.setFoodName(foodName.trim());
				}
				if(foodType!=null && !"".equals(foodType)){
					food.setTypeName(foodType.trim());
				}
				
				//ͨ�÷�ҳBean
				PageBean<Food> pageBean = new PageBean<Food>();
				pageBean.setMaxResult(4);
				
				//Ĭ��Ϊ��һҳ
				int intPage=1;
				//��ȡҳ�洫�����Ĳ���
				String currentPage=request.getParameter("currentPage");
				if (currentPage != null && !"".equals(currentPage)) {
					intPage=Integer.parseInt(currentPage);
				}
				
				pageBean.setCurrentPage(intPage);
				
				foodService.setPageBean(pageBean, food);
				
				//����������
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("food", food);
				
				return request.getRequestDispatcher("/app/caidan.jsp");

		
	}
	
	public Object findFoodById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ѯ���в�ϵ
		List<FoodType> types=typeService.getAllFoodType(new FoodType());				
		//����������
		request.setAttribute("types", types);
		
		String foodId = request.getParameter("foodId");
		
		Food food=foodService.findFoodById(Integer.parseInt(foodId));
		request.setAttribute("food", food);
		return request.getRequestDispatcher("/app/caixiangxi.jsp");
	}
}
