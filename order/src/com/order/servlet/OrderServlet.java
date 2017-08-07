package com.order.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.entity.Food;
import com.order.entity.Order;
import com.order.entity.OrderDetail;
import com.order.service.OrderDetailService;

public class OrderServlet extends BaseServlet {

	/**
	 * 添加购物车
	 */
	public Object putCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String foodId=request.getParameter("foodId");
		Food food = foodService.findFoodById(Integer.parseInt(foodId));
		
		//session 是否存在购物车
		Map<Food, Integer> sionCart=(Map<Food, Integer>) request.getSession().getAttribute("cart");
		
		if (sionCart == null) {//购物车不存在
			//map存储菜品  购物车
			Map<Food, Integer> cart = new HashMap<Food, Integer>();
			cart.put(food,1);
			
			//放入session作用域
			request.getSession().setAttribute("cart", cart);
			
		}else {//购物车存在
			if (sionCart.containsKey(food)) {//存在购物车   要 修改
				
				//修改的食品数量值
				String num = request.getParameter("num");
				if (num != null && !"".equals(num) ) {
					Integer inNum = 0;
					inNum = Integer.parseInt(num);
					
					//通过手动输入修改数量
					sionCart.put(food, inNum);
				}else{
					//获取食品的数量
					Integer count = sionCart.get(food);
					//重复加入购物车
					sionCart.put(food, ++count);
					
				}
				
			}else { //不存在购物车  新添加到购物车
				sionCart.put(food, 1);
			}
			request.getSession().setAttribute("cart", sionCart);
		}
		return "/app/clientCart.jsp";
	}
	
	/**
	 * 下单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object takeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object url = null;
		//获取购物车的数据
		HttpSession session = request.getSession(true);
		Map<Food,Integer> m = (Map<Food, Integer>) session.getAttribute("cart"); 
		//获取餐桌ID
		String table_id = (String) session.getAttribute("table_id");
		
		//新建订单对象
		Order order = new Order();
		order.setTableId(Integer.parseInt(table_id));
		
		Set<Entry<Food,Integer>> entrySet = m.entrySet();
		//定义总价钱
		int sum = 0;
		for (Entry<Food, Integer> entry : entrySet) {
			Food food = entry.getKey();
			Integer count = entry.getValue();
			sum += food.getPrice() * count;
		}
		//订单的总价格
		order.setTotalPrice(sum);
		//订单的时间
		order.setOrderDate(new Date());
		//数据库保存订单
		
		/********************以下是插入 订单与菜品  详细表*****************************/
		//获取保存订单id，供订单详细使用
		int orderId = orderService.getMaxId();
		
		//创建订单详细对象
		OrderDetail detail = new OrderDetail();
		
		for (Entry<Food, Integer> entry : entrySet) {
			Food food = entry.getKey();
			Integer count = entry.getValue();
			detail.setFood_id(food.getId());
			detail.setOrderId(orderId);
			detail.setFoodCount(count);
			orderDetailService.add(detail);//保存订单详细
		}
		
		//清空购物车
		session.setAttribute("cart", null);
		
		//获取一条订单
		Order order1 = orderService.findOrderById(orderId);
		request.getSession().setAttribute("order", order1);
		//获取订单对应的 菜单明细
		List<OrderDetail> orderDetail = orderDetailService.query(orderId);
		request.getSession().setAttribute("orderDetail", orderDetail);
		
		url = "/app/clientOrderList.jsp";
		
		return url;
	}
}
