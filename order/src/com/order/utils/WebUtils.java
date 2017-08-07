package com.order.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	
	//ͨ�õ���ת
	public static void goTo(Object url,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if(url instanceof RequestDispatcher){
			//ת��
			((RequestDispatcher) url).forward(request, response);
		}
		
		if(url instanceof String){
			//�ض���
			response.sendRedirect(request.getContextPath() + url);
		}
	}
}
