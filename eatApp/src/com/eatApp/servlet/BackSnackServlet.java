package com.eatApp.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eatApp.entity.Snack;
import com.eatApp.service.SnackService;
import com.eatApp.service.impl.SnackServiceImpl;
import com.eatApp.utils.PageBean;


/**
 * 后台  零食管理
 */

public class BackSnackServlet extends HttpServlet {
	
	SnackService snackService = new SnackServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			list(request,response);
		}else if ("add".equals(method)) {
			add(request,response);
		}else if ("updateUI".equals(method)) {
			updateUI(request,response);
		}else if ("update".equals(method)) {
			update(request,response);
		}else if ("delete".equals(method)) {
			delete(request,response);
		}
	}

	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1,创建工程对象
		FileItemFactory factory = new DiskFileItemFactory();
		// 2、创建一个文件的核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3、参数设置
		upload.setFileSizeMax(1024 * 1024 * 5);// 设置单个文件上传不能超过5M
		upload.setSizeMax(1024 * 1204 * 20);// 上传文件的总大小限制 20M
		upload.setHeaderEncoding("UTF-8");// 设置中文编码格式

		// 封装页面的参数
		Snack snack = new Snack();

		// 判断是否是上传Snack
		if (upload.isMultipartContent(request)) {
			// 处理表单的数据

			try {
				List<FileItem> items = upload.parseRequest(request);

				for (FileItem item : items) {
					if (item.isFormField()) {
						String name = item.getFieldName(); // 得到属性名
						String value = item.getString(); // 得到属性的值
						value = new String(value.getBytes("iso-8859-1"), "utf-8");
						System.out.println(name + ":" + value);

						// 给Snack对象属性赋值
						BeanUtils.setProperty(snack, name, value);

					} else {// 上传的文件框
						System.out.println(item.getName());

						// 得到web的真实路径
						String path = this.getServletContext().getRealPath("/upload");

						File imgPath = new File(path);
						if (!imgPath.exists()) {
							imgPath.mkdir();// 创建 upload
						}

						// 得到文件的后缀
						String filePath = item.getName();
						String hz = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());

						String uuid = UUID.randomUUID().toString();
						// 防止文件名重复
						String newFileName = uuid + "#" + hz;

						// 给Snack的img属性赋值
						BeanUtils.setProperty(snack, "image", "/app/images/" + newFileName);

						File file = new File(path, newFileName);

						// 上传
						item.write(file);
						// 删除临时文件
						item.delete();

					}

				}

				// 保存数据库
				snackService.updateSnack(snack);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 跳转
		request.getRequestDispatcher("BackSnackServlet?method=list").forward(request, response);
		
	}


	public void updateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int snackId = 0;
		String id = request.getParameter("id");
		
		if (null != id && !id.equals("")) {
			snackId = Integer.parseInt(id);
		}
		
		Snack snack = snackService.findSnackById(snackId);
		
		//放入作用域
		request.setAttribute("snack", snack);
		
		//跳转
		request.getRequestDispatcher("back/shop/update.jsp").forward(request, response);
	}


	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1,创建工程对象
		FileItemFactory factory = new DiskFileItemFactory();
		//2、创建一个文件的核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory);
		//3、参数设置
		upload.setFileSizeMax(1024*1024*5);//设置单个文件上传不能超过5M
		upload.setSizeMax(1024*1204*20);//上传文件的总大小限制  20M
		upload.setHeaderEncoding("UTF-8");//设置中文编码格式
		
		//封装页面的参数
		Snack snack = new Snack();
		
		//判断是否是上传Snack
		if (upload.isMultipartContent(request)) {
			//处理表单的数据
			
			try {
				List<FileItem> items=upload.parseRequest(request);
				
				for (FileItem item : items) {
					if (item.isFormField()) {
						String name = item.getFieldName(); //得到属性名
						String value = item.getString();  //得到属性的值
						value = new String(value.getBytes("iso-8859-1"),"utf-8");
						System.out.println(name+":"+value);
						
						//给Snack对象属性赋值
						BeanUtils.setProperty(snack, name, value);
						
						
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
						String hz = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.length());
						
						String uuid = UUID.randomUUID().toString();
						//防止文件名重复
						String newFileName = uuid+"#"+hz;
						
						//给Snack的img属性赋值
						BeanUtils.setProperty(snack, "image", "/app/images/"+newFileName);
						
						File file=new File(path,newFileName);
						
						//上传
						item.write(file);
						//删除临时文件
						item.delete();
					
					
					}
					
				}
				
				//保存数据库
				snackService.addSnack(snack);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		//跳转
		request.getRequestDispatcher("BackSnackServlet?method=list").forward(request, response);
	}


	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (null != id && !id.equals("")) {
			snackService.deleteSnackById(Integer.parseInt(id));
		}
		request.getRequestDispatcher("BackSnackServlet?method=list").forward(request, response);
		
	}


	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面传递过来的参数
		String snackName = request.getParameter("snackName");
		
		Snack snack = new Snack();
		if (null != snackName && !"".equals(snackName)) {
			snack.setSnackName(snackName.trim());
		}
		
		//通用分页Pagebean
		PageBean<Snack> pageBean = new PageBean<Snack>();
		pageBean.setMaxResult(5);
				
		//默认为第一页
		int intPage = 1;
				
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null && !"".equals(currentPage)) {
			intPage = Integer.parseInt(currentPage);
		}
		pageBean.setCurrent(intPage);
		snackService.setPageBean(pageBean, snack);
			
		//放入作用域
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("snack", snack);
		//跳转
		request.getRequestDispatcher("back/shop/shopInformation.jsp").forward(request, response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
