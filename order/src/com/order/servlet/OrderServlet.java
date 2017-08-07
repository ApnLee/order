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
	 * ��ӹ��ﳵ
	 */
	public Object putCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String foodId=request.getParameter("foodId");
		Food food = foodService.findFoodById(Integer.parseInt(foodId));
		
		//session �Ƿ���ڹ��ﳵ
		Map<Food, Integer> sionCart=(Map<Food, Integer>) request.getSession().getAttribute("cart");
		
		if (sionCart == null) {//���ﳵ������
			//map�洢��Ʒ  ���ﳵ
			Map<Food, Integer> cart = new HashMap<Food, Integer>();
			cart.put(food,1);
			
			//����session������
			request.getSession().setAttribute("cart", cart);
			
		}else {//���ﳵ����
			if (sionCart.containsKey(food)) {//���ڹ��ﳵ   Ҫ �޸�
				
				//�޸ĵ�ʳƷ����ֵ
				String num = request.getParameter("num");
				if (num != null && !"".equals(num) ) {
					Integer inNum = 0;
					inNum = Integer.parseInt(num);
					
					//ͨ���ֶ������޸�����
					sionCart.put(food, inNum);
				}else{
					//��ȡʳƷ������
					Integer count = sionCart.get(food);
					//�ظ����빺�ﳵ
					sionCart.put(food, ++count);
					
				}
				
			}else { //�����ڹ��ﳵ  ����ӵ����ﳵ
				sionCart.put(food, 1);
			}
			request.getSession().setAttribute("cart", sionCart);
		}
		return "/app/clientCart.jsp";
	}
	
	/**
	 * �µ�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object takeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object url = null;
		//��ȡ���ﳵ������
		HttpSession session = request.getSession(true);
		Map<Food,Integer> m = (Map<Food, Integer>) session.getAttribute("cart"); 
		//��ȡ����ID
		String table_id = (String) session.getAttribute("table_id");
		
		//�½���������
		Order order = new Order();
		order.setTableId(Integer.parseInt(table_id));
		
		Set<Entry<Food,Integer>> entrySet = m.entrySet();
		//�����ܼ�Ǯ
		int sum = 0;
		for (Entry<Food, Integer> entry : entrySet) {
			Food food = entry.getKey();
			Integer count = entry.getValue();
			sum += food.getPrice() * count;
		}
		//�������ܼ۸�
		order.setTotalPrice(sum);
		//������ʱ��
		order.setOrderDate(new Date());
		//���ݿⱣ�涩��
		
		/********************�����ǲ��� �������Ʒ  ��ϸ��*****************************/
		//��ȡ���涩��id����������ϸʹ��
		int orderId = orderService.getMaxId();
		
		//����������ϸ����
		OrderDetail detail = new OrderDetail();
		
		for (Entry<Food, Integer> entry : entrySet) {
			Food food = entry.getKey();
			Integer count = entry.getValue();
			detail.setFood_id(food.getId());
			detail.setOrderId(orderId);
			detail.setFoodCount(count);
			orderDetailService.add(detail);//���涩����ϸ
		}
		
		//��չ��ﳵ
		session.setAttribute("cart", null);
		
		//��ȡһ������
		Order order1 = orderService.findOrderById(orderId);
		request.getSession().setAttribute("order", order1);
		//��ȡ������Ӧ�� �˵���ϸ
		List<OrderDetail> orderDetail = orderDetailService.query(orderId);
		request.getSession().setAttribute("orderDetail", orderDetail);
		
		url = "/app/clientOrderList.jsp";
		
		return url;
	}
}
