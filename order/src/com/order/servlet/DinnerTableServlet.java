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
 * �����Ĺ�����Ʋ�
 * @author ApnLee
 *
 */
public class DinnerTableServlet extends BaseServlet {
	

	/**
	 * ��ʾ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object url=null;
		
		//����ҳ�洫�ݹ�������������
		String name=request.getParameter("keyword");
		//��װ��ѯ����
		DinnerTable table=new DinnerTable();
		if(name!=null && !"".equals(name)){
			table.setTableName(name);
		}
		//��ѯ���в���
		List<DinnerTable> tables=tableService.getAllDinnerTable(table);
		//����������
		request.setAttribute("tables", tables);
		request.setAttribute("keyword", name);
		
		//��ת
		request.getRequestDispatcher("/sys/table/tableList.jsp").forward(request, response);
		
		return url;
	
	}
	/**
	 * ����   Ԥ��
	 */
	public Object update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ���ݹ����Ĳ���
		String id=request.getParameter("id");
		if (id!=null && !"".equals(id)) {
			DinnerTable table=tableService.findTableById(Integer.parseInt(id));
			//����
			tableService.updateStatus(table);
		}
		
		return list(request, response);
	
	}
	
	/**
	 * ��Ӳ���
	 */
	public Object add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡҳ�洫�����Ĳ���
		String tableName=request.getParameter("bName");
		//��װ
		DinnerTable table=new DinnerTable();
		table.setTableName(tableName);
		//����ҵ���
		tableService.addDinnerTable(table);
		
		//��ת�����б�
		return list(request, response);
	}
	
	/**
	 * ɾ��
	 */
	public Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡҳ������Ĳ�������
		String id=request.getParameter("id");
		if(id != null && !"".equals(id)){
			tableService.deleteTableById(Integer.parseInt(id));
		}
		
		return list(request, response);
		
		
	}


}
