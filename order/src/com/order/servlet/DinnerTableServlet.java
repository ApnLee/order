package com.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.entity.DinnerTable;
import com.order.service.DinnerTableService;
import com.order.service.impl.DinnerTableServiceImpl;
/**
 * 餐桌的管理控制层
 * @author ApnLee
 *
 */
public class DinnerTableServlet extends BaseServlet {
	

	/**
	 * 显示
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object url=null;
		
		//接收页面传递过来的搜索条件
		String name=request.getParameter("keyword");
		//封装查询参数
		DinnerTable table=new DinnerTable();
		if(name!=null && !"".equals(name)){
			table.setTableName(name);
		}
		//查询所有餐桌
		List<DinnerTable> tables=tableService.getAllDinnerTable(table);
		//放入作用域
		request.setAttribute("tables", tables);
		request.setAttribute("keyword", name);
		
		//跳转
		request.getRequestDispatcher("/sys/table/tableList.jsp").forward(request, response);
		
		return url;
	
	}
	/**
	 * 退桌   预定
	 */
	public Object update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取传递过来的参数
		String id=request.getParameter("id");
		if (id!=null && !"".equals(id)) {
			DinnerTable table=tableService.findTableById(Integer.parseInt(id));
			//更新
			tableService.updateStatus(table);
		}
		
		return list(request, response);
	
	}
	
	/**
	 * 添加餐桌
	 */
	public Object add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取页面传过来的参数
		String tableName=request.getParameter("bName");
		//封装
		DinnerTable table=new DinnerTable();
		table.setTableName(tableName);
		//调用业务层
		tableService.addDinnerTable(table);
		
		//跳转餐桌列表
		return list(request, response);
	}
	
	/**
	 * 删除
	 */
	public Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取页面输入的餐桌名称
		String id=request.getParameter("id");
		if(id != null && !"".equals(id)){
			tableService.deleteTableById(Integer.parseInt(id));
		}
		
		return list(request, response);
		
		
	}


}
