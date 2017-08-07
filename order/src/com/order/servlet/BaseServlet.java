package com.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.entity.DinnerTable;
import com.order.entity.Order;
import com.order.service.DinnerTableService;
import com.order.service.FoodService;
import com.order.service.FoodTypeService;
import com.order.service.OrderDetailService;
import com.order.service.OrderService;
import com.order.service.impl.DinnerTableServiceImpl;
import com.order.service.impl.FoodServiceImpl;
import com.order.service.impl.FoodTypeServiceImpl;
import com.order.service.impl.OrderDetailServiceImpl;
import com.order.service.impl.OrderServiceImpl;
import com.order.utils.WebUtils;

public class BaseServlet extends HttpServlet {

	//餐桌类型的业务层
	DinnerTableService tableService=new DinnerTableServiceImpl();
	//菜系类型的业务层
	FoodTypeService typeService = new FoodTypeServiceImpl();
	//菜品的业务层
	FoodService foodService=new FoodServiceImpl();
	//订单的业务层
	OrderService orderService=new OrderServiceImpl();
	
	OrderDetailService orderDetailService = new OrderDetailServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object url=null;//返回url
		
		try {
			//指定跳转要执行的的方法
			String methodName=request.getParameter("method");
			System.out.println("返回当前执行的servlet的字节码："+this.getClass());
			//获取当前执行的Servlet的字节码
			Class clazz = this.getClass();
			//根据方法名，获取指定的方法
			Method method=clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class );
		
			System.out.println("---------------"+method);
			//方法执行  url  代表的是方法返回 跳转的url地址
			url = method.invoke(this,request, response); //转发
			
			System.out.println(url);
		 
		} catch (Exception e) {
			url="/error/error.jsp"; //重定向
		}
		
		//跳转
		WebUtils.goTo(url, request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	
	}

}
