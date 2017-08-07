package com.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.entity.FoodType;
import com.order.service.FoodTypeService;
import com.order.service.impl.FoodTypeServiceImpl;

public class FoodTypeServlet extends BaseServlet {
	

	/**
	 * ��ת���޸�ҳ��
	 */
	public Object updateUI(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		Object url = null;
		
		//��ȡҳ�洫�����Ĳ���
		String id=request.getParameter("typeId");
		
		//����ID���Ҷ�Ӧ��ʳƷ����
		FoodType type=typeService.findTypeById(Integer.parseInt(id));
		
		
		//����������
		request.setAttribute("type", type);
		
		url=request.getRequestDispatcher("/sys/type/updateType.jsp");
		
		return url;
		
	}


	/**
	 * ��ѯ���еĲ����б�
	 */
	public Object list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object url = null;
		//��ȡҳ�洫�����Ĳ���
		String name=request.getParameter("keyword");
		//��װ���Ҳ���
		FoodType type=new FoodType();
		if(name!=null && !"".equals(name)){
			type.setTypeName(name);
		}
		
		//��ѯ���в�ϵ
		List<FoodType> types=typeService.getAllFoodType(type);
		
		//����������
		request.setAttribute("types", types);
		request.setAttribute("keyword", name);
		
		//��ת
		request.getRequestDispatcher("/sys/type/typeList.jsp").forward(request, response);
		
		return url;
	
	}
	
	/**
	 * ����
	 */
	public Object update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
						
				//��ȡ���ݹ����Ĳ���
				String id=request.getParameter("id");
				String name=request.getParameter("name");
				//��װ
				FoodType type=new FoodType();
				if(id != null && !"".equals(id)){
					type.setId(Integer.parseInt(id));	
				}
	
				type.setTypeName(name);
				
				typeService.updateType(type);
				
				System.out.println(type.getTypeName());
				
				return list(request, response);

	}
	
	/**
	 * ���
	 */
	public Object add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡ���ݹ����Ĳ���
		String typeName=request.getParameter("name");
		//��װ����
		FoodType type=new FoodType();
		type.setTypeName(typeName);
		
		typeService.addFoodType(type);
		
		return list(request, response);

	}
	
	/**
	 * ɾ��
	 */
	public Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡ���ݹ����Ĳ���
		String id=request.getParameter("id");
		if(id != null && !"".equals(id)){
			typeService.deleteTypeById(Integer.parseInt(id));
		}
		
		return list(request, response);

	}
	

}
