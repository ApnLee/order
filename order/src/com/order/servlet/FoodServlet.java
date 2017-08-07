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
		pageBean.setMaxResult(5);
		
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
		
		return request.getRequestDispatcher("/sys/food/foodList.jsp");

	}
	
	public Object addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询所有的菜系
		List<FoodType> types=typeService.getAllFoodType(new FoodType());
		
		request.setAttribute("types", types);
		
		return request.getRequestDispatcher("/sys/food/saveFood.jsp");
		
	}
	
	public Object add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//1,创建工程对象
			FileItemFactory factory = new DiskFileItemFactory();
			//2、创建一个文件的核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3、参数设置
			upload.setFileSizeMax(1024*1024*5);//设置单个文件上传不能超过5M
			upload.setSizeMax(1024*1204*20);//上传文件的总大小限制  20M
			upload.setHeaderEncoding("UTF-8");//设置中文编码格式
			
			//封装页面的参数
			Food food = new Food();
			
			//判断是否是上传的菜单
			if (upload.isMultipartContent(request)) {
				//处理表单的数据
				
				try {
					List<FileItem> items=upload.parseRequest(request);
					
					for (FileItem item : items) {
						if (item.isFormField()) {
							String name = item.getFieldName(); //得到属性名
							String value = item.getString();  //得到属性的值
							value = new String(value.getBytes("iso-8859-1"),"utf-8");
							System.out.println(name+value);
							
							//给Food对象属性赋值
							BeanUtils.setProperty(food, name, value);
							
							
						}else{//上传的文件框
							System.out.println(item.getName());
							
							//得到web的真实路径
							String path = this.getServletContext().getRealPath("/upload");
							
							File imgPath = new File(path);
							if(!imgPath.exists()){
								imgPath.mkdir();//创建 upload
							}
							
							//得到文件的后缀
							String filePath = item.getName();
							String hzui = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.length());
							
							String uuid = UUID.randomUUID().toString();
							//防止文件名重复
							String newFileName = uuid+"#"+hzui;
							
							//给food的img属性赋值
							BeanUtils.setProperty(food, "img", "upload\\"+newFileName);
							
							File file=new File(path,newFileName);
							
							//上传
							item.write(file);
							//删除临时文件
							item.delete();
						
						
						}
						
					}
					
					//保存数据库
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
		
		//1,创建工程对象
		FileItemFactory factory = new DiskFileItemFactory();
		//2、创建一个文件的核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory);
		//3、参数设置
		upload.setFileSizeMax(1024*1024*5);//设置单个文件上传不能超过5M
		upload.setSizeMax(1024*1204*20);//上传文件的总大小限制  20M
		upload.setHeaderEncoding("UTF-8");//设置中文编码格式
		
		//封装页面的参数
		Food food = new Food();
		
		//判断是否是上传的菜单
		if (upload.isMultipartContent(request)) {
			//处理表单的数据
			
			try {
				List<FileItem> items=upload.parseRequest(request);
				
				for (FileItem item : items) {
					if (item.isFormField()) {
						String name = item.getFieldName(); //得到属性名
						String value = item.getString();  //得到属性的值
						value = new String(value.getBytes("iso-8859-1"),"utf-8");
						System.out.println(name+value);
						
						//给Food对象属性赋值
						BeanUtils.setProperty(food, name, value);
						
						
					}else{//上传的文件框
						System.out.println(item.getName());
						
						//得到web的真实路径
						String path = this.getServletContext().getRealPath("/upload");
						
						File imgPath = new File(path);
						if(!imgPath.exists()){
							imgPath.mkdir();//创建 upload
						}
						
						//得到文件的后缀
						String filePath = item.getName();
						if (filePath != null && !"".equals(filePath)) {
							String hzui = filePath.substring(filePath.lastIndexOf("."), filePath.length());
							
							String uuid = UUID.randomUUID().toString().replace("-", "");
							//防止文件名重复
							String newFileName = uuid+"#"+hzui;
							
							//给food的img属性赋值
							BeanUtils.setProperty(food, "img", "upload/"+newFileName);
							
							File file=new File(path,newFileName);
							
							//上传
							item.write(file);
							//删除临时文件
							item.delete();
						}
						
					
					
					}
					
				}
				
				//保存数据库
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
		//获取页面传过来的参数
		String id=request.getParameter("id");
		if(id!=null && !"".equals(id)){
			foodService.deleteFoodById(Integer.parseInt(id));
		}
		return list(request, response);
	}
		
	

}
