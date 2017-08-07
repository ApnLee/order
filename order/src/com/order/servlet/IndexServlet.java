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

		  //存放所有餐桌  
		  this.getServletContext().setAttribute("tables", tableService.getAllDinnerTable(new DinnerTable()));

		  return request.getRequestDispatcher("/app/index.jsp");
		
	}

	public Object menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询所有菜系
		List<FoodType> types=typeService.getAllFoodType(new FoodType());				
		//放入作用域
		request.setAttribute("types", types);
		
				//获取页面传过来的查询条件
				String foodName=request.getParameter("foodName");//名称
				String foodType=request.getParameter("foodType");//类型
				
				//封装参数
				Food food = new Food();
				if(foodName!=null && !"".equals(foodName)){
					food.setFoodName(foodName.trim());
				}
				if(foodType!=null && !"".equals(foodType)){
					food.setTypeName(foodType.trim());
				}
				
				//通用分页Bean
				PageBean<Food> pageBean = new PageBean<Food>();
				pageBean.setMaxResult(4);
				
				//默认为第一页
				int intPage=1;
				//获取页面传过来的参数
				String currentPage=request.getParameter("currentPage");
				if (currentPage != null && !"".equals(currentPage)) {
					intPage=Integer.parseInt(currentPage);
				}
				
				pageBean.setCurrentPage(intPage);
				
				foodService.setPageBean(pageBean, food);
				
				//放入作用域
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("food", food);
				
				return request.getRequestDispatcher("/app/caidan.jsp");

		
	}
	
	public Object findFoodById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询所有菜系
		List<FoodType> types=typeService.getAllFoodType(new FoodType());				
		//放入作用域
		request.setAttribute("types", types);
		
		String foodId = request.getParameter("foodId");
		
		Food food=foodService.findFoodById(Integer.parseInt(foodId));
		request.setAttribute("food", food);
		return request.getRequestDispatcher("/app/caixiangxi.jsp");
	}
}
