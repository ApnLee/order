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
	 * 跳转到修改页面
	 */
	public Object updateUI(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		Object url = null;
		
		//获取页面传过来的参数
		String id=request.getParameter("typeId");
		
		//根据ID查找对应的食品类型
		FoodType type=typeService.findTypeById(Integer.parseInt(id));
		
		
		//放入作用域
		request.setAttribute("type", type);
		
		url=request.getRequestDispatcher("/sys/type/updateType.jsp");
		
		return url;
		
	}


	/**
	 * 查询所有的餐桌列表
	 */
	public Object list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object url = null;
		//获取页面传过来的参数
		String name=request.getParameter("keyword");
		//封装查找参数
		FoodType type=new FoodType();
		if(name!=null && !"".equals(name)){
			type.setTypeName(name);
		}
		
		//查询所有菜系
		List<FoodType> types=typeService.getAllFoodType(type);
		
		//放入作用域
		request.setAttribute("types", types);
		request.setAttribute("keyword", name);
		
		//跳转
		request.getRequestDispatcher("/sys/type/typeList.jsp").forward(request, response);
		
		return url;
	
	}
	
	/**
	 * 更新
	 */
	public Object update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
						
				//获取传递过来的参数
				String id=request.getParameter("id");
				String name=request.getParameter("name");
				//封装
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
	 * 添加
	 */
	public Object add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取传递过来的参数
		String typeName=request.getParameter("name");
		//封装参数
		FoodType type=new FoodType();
		type.setTypeName(typeName);
		
		typeService.addFoodType(type);
		
		return list(request, response);

	}
	
	/**
	 * 删除
	 */
	public Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取传递过来的参数
		String id=request.getParameter("id");
		if(id != null && !"".equals(id)){
			typeService.deleteTypeById(Integer.parseInt(id));
		}
		
		return list(request, response);

	}
	

}
