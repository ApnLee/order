package com.order.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		//����post�������������
		request.setCharacterEncoding("utf-8");
		//�����������ָ���ı��뷽ʽ��
		response.setContentType("text/html;charset=utf-8");
		//������Ӧ��ָ�������ʽ
		response.setCharacterEncoding("utf-8");
		
		/**
	    get request.getParameter("")  ��������
	            ��  HttpServletRequest��Ŀ�꣩            
	    Proxy.newProxyInstance     ��Ŀ�괴��һ���������  
	    */
		
		HttpServletRequest httpRequest=(HttpServletRequest) Proxy.newProxyInstance(
				HttpServletRequest.class.getClassLoader(),//��ҪĿ������������� 
				new Class[]{HttpServletRequest.class}, 
				new InvocationHandler() {
					/**
					 * Method method:  Ŀ������ϵ����з���
					 * Object[] args:  Ŀ������Ϸ������еĲ���
					 * ���磺   Ŀ�귽��       getParameter(String name);
					 * 		method = getParameter
					 * 		args=new Object[]{name}
					 */
							
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						//System.out.println("requestĿ�����ķ�����"+method.getName());
						
						//��Ŀ������getParameter������չ
						if("getParameter".equals(method.getName())){
							//<input type="text" name="username">
							String username=request.getParameter(args[0].toString());
							
							//�����get��������������
							if("GET".equalsIgnoreCase(request.getMethod())){
								if(username != null && !"".equals(username)){
									username=new String(username.getBytes("iso-8859-1"),"utf-8");//�����������
								}
								
								return username;
							}else{
								return method.invoke(request, args);//�����κδ���ֱ�ӵ���Ŀ�����ķ���
							
							}
						}else{
							
							return method.invoke(request, args);////�����κδ���ֱ�ӵ���Ŀ�����ķ���
						}
					}
				});
		
		//ִ��servlet
		chain.doFilter(httpRequest,response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	

	}

}
