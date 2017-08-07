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

	//�������͵�ҵ���
	DinnerTableService tableService=new DinnerTableServiceImpl();
	//��ϵ���͵�ҵ���
	FoodTypeService typeService = new FoodTypeServiceImpl();
	//��Ʒ��ҵ���
	FoodService foodService=new FoodServiceImpl();
	//������ҵ���
	OrderService orderService=new OrderServiceImpl();
	
	OrderDetailService orderDetailService = new OrderDetailServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object url=null;//����url
		
		try {
			//ָ����תҪִ�еĵķ���
			String methodName=request.getParameter("method");
			System.out.println("���ص�ǰִ�е�servlet���ֽ��룺"+this.getClass());
			//��ȡ��ǰִ�е�Servlet���ֽ���
			Class clazz = this.getClass();
			//���ݷ���������ȡָ���ķ���
			Method method=clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class );
		
			System.out.println("---------------"+method);
			//����ִ��  url  ������Ƿ������� ��ת��url��ַ
			url = method.invoke(this,request, response); //ת��
			
			System.out.println(url);
		 
		} catch (Exception e) {
			url="/error/error.jsp"; //�ض���
		}
		
		//��ת
		WebUtils.goTo(url, request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	
	}

}
