package com.order.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.order.entity.Food;
import com.order.entity.FoodType;
import com.order.service.FoodService;
import com.order.service.impl.FoodServiceImpl;
import com.order.utils.PageBean;

public class FoodServlet extends BaseServlet {

	
	public Object list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		pageBean.setMaxResult(5);
		
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
		
		return request.getRequestDispatcher("/sys/food/foodList.jsp");

	}
	
	public Object addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ѯ���еĲ�ϵ
		List<FoodType> types=typeService.getAllFoodType(new FoodType());
		
		request.setAttribute("types", types);
		
		return request.getRequestDispatcher("/sys/food/saveFood.jsp");
		
	}
	
	public Object add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//1,�������̶���
			FileItemFactory factory = new DiskFileItemFactory();
			//2������һ���ļ��ĺ��Ĺ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3����������
			upload.setFileSizeMax(1024*1024*5);//���õ����ļ��ϴ����ܳ���5M
			upload.setSizeMax(1024*1204*20);//�ϴ��ļ����ܴ�С����  20M
			upload.setHeaderEncoding("UTF-8");//�������ı����ʽ
			
			//��װҳ��Ĳ���
			Food food = new Food();
			
			//�ж��Ƿ����ϴ��Ĳ˵�
			if (upload.isMultipartContent(request)) {
				//�����������
				
				try {
					List<FileItem> items=upload.parseRequest(request);
					
					for (FileItem item : items) {
						if (item.isFormField()) {
							String name = item.getFieldName(); //�õ�������
							String value = item.getString();  //�õ����Ե�ֵ
							value = new String(value.getBytes("iso-8859-1"),"utf-8");
							System.out.println(name+value);
							
							//��Food�������Ը�ֵ
							BeanUtils.setProperty(food, name, value);
							
							
						}else{//�ϴ����ļ���
							System.out.println(item.getName());
							
							//�õ�web����ʵ·��
							String path = this.getServletContext().getRealPath("/upload");
							
							File imgPath = new File(path);
							if(!imgPath.exists()){
								imgPath.mkdir();//���� upload
							}
							
							//�õ��ļ��ĺ�׺
							String filePath = item.getName();
							String hzui = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.length());
							
							String uuid = UUID.randomUUID().toString();
							//��ֹ�ļ����ظ�
							String newFileName = uuid+"#"+hzui;
							
							//��food��img���Ը�ֵ
							BeanUtils.setProperty(food, "img", "upload\\"+newFileName);
							
							File file=new File(path,newFileName);
							
							//�ϴ�
							item.write(file);
							//ɾ����ʱ�ļ�
							item.delete();
						
						
						}
						
					}
					
					//�������ݿ�
					foodService.addFood(food);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
		
		return list(request, response);
		
	}
	
	public Object updateUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String foodId=request.getParameter("foodId");
		if(foodId!=null && !"".equals(foodId)){
			Food food=foodService.findFoodById(Integer.parseInt(foodId));
			request.setAttribute("food", food);
		}
		
		List<FoodType> types=typeService.getAllFoodType(new FoodType());
		request.setAttribute("types", types);
		
		return request.getRequestDispatcher("/sys/food/updateFood.jsp");
	} 
	
	public Object update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1,�������̶���
		FileItemFactory factory = new DiskFileItemFactory();
		//2������һ���ļ��ĺ��Ĺ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		//3����������
		upload.setFileSizeMax(1024*1024*5);//���õ����ļ��ϴ����ܳ���5M
		upload.setSizeMax(1024*1204*20);//�ϴ��ļ����ܴ�С����  20M
		upload.setHeaderEncoding("UTF-8");//�������ı����ʽ
		
		//��װҳ��Ĳ���
		Food food = new Food();
		
		//�ж��Ƿ����ϴ��Ĳ˵�
		if (upload.isMultipartContent(request)) {
			//�����������
			
			try {
				List<FileItem> items=upload.parseRequest(request);
				
				for (FileItem item : items) {
					if (item.isFormField()) {
						String name = item.getFieldName(); //�õ�������
						String value = item.getString();  //�õ����Ե�ֵ
						value = new String(value.getBytes("iso-8859-1"),"utf-8");
						System.out.println(name+value);
						
						//��Food�������Ը�ֵ
						BeanUtils.setProperty(food, name, value);
						
						
					}else{//�ϴ����ļ���
						System.out.println(item.getName());
						
						//�õ�web����ʵ·��
						String path = this.getServletContext().getRealPath("/upload");
						
						File imgPath = new File(path);
						if(!imgPath.exists()){
							imgPath.mkdir();//���� upload
						}
						
						//�õ��ļ��ĺ�׺
						String filePath = item.getName();
						if (filePath != null && !"".equals(filePath)) {
							String hzui = filePath.substring(filePath.lastIndexOf("."), filePath.length());
							
							String uuid = UUID.randomUUID().toString().replace("-", "");
							//��ֹ�ļ����ظ�
							String newFileName = uuid+"#"+hzui;
							
							//��food��img���Ը�ֵ
							BeanUtils.setProperty(food, "img", "upload/"+newFileName);
							
							File file=new File(path,newFileName);
							
							//�ϴ�
							item.write(file);
							//ɾ����ʱ�ļ�
							item.delete();
						}
						
					
					
					}
					
				}
				
				//�������ݿ�
				foodService.updateFood(food);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		return list(request, response);
				
	}
	
	public Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡҳ�洫�����Ĳ���
		String id=request.getParameter("id");
		if(id!=null && !"".equals(id)){
			foodService.deleteFoodById(Integer.parseInt(id));
		}
		return list(request, response);
	}
		
	

}
