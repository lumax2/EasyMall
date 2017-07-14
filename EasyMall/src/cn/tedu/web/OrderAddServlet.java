package cn.tedu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Order;
import cn.tedu.entity.OrderItem;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1313239149179221743L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//1����session�л�ȡ�û���Ϣ
		Object userObj = request.getSession().
					getAttribute("user");
		if(userObj==null){//δ��¼
			request.getRequestDispatcher("/login.jsp").
				forward(request, response);
			return;
		}
		//2���ѵ�¼,ǿ������ת������ȡ�û���id
		int user_id = ((User)userObj).getId();
		//3����session�л�ȡcart
		Object cartObj = request.getSession().
				getAttribute("cart");
		if(cartObj==null){//sessionʧЧ
			response.sendRedirect(request.
					getContextPath()+"/index.jsp");
			return;
		}
		//4����Ϊnull��ǿ������ת��
		Map<Product,Integer> cart = (Map<Product,Integer>)cartObj;
		//5��cart->Order order,List<OrderItem> oiList
		//5.1����order,oiList
		Order order = new Order();
		List<OrderItem> oiList = new ArrayList<OrderItem>();
		//5.2Ϊorder��ֵ
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());//?
		order.setPaystate(0);//0:��ʾδ֧��
		order.setUser_id(user_id);
		order.setReceiverinfo(request.getParameter("receiverinfo"));
		//5.3���嶩�����
		double money = 0;
		//5.5����
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			OrderItem item = new OrderItem();
			item.setOrder_id(order.getId());
			item.setProduct_id(entry.getKey().getId());
			item.setBuynum(entry.getValue());
			//������
			money+= entry.getKey().getPrice()*entry.getValue();
			//�����ҡ�����ǧ��Ҫ���ǽ�item��ӵ�oiList
			oiList.add(item);
		}
		//5.4��ֵ
		order.setMoney(money);
		//6.����ҵ�񷽷�
		OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
		try{
			orderService.addOrder(order, oiList);
			//7��ӳɹ�
			response.getWriter().write("������ӳɹ���");
			response.setHeader("Refresh", "3;url="+request.getContextPath()+"/servlet/OrderListServlet");
		}catch (MsgException e) {
			//8���ʧ��
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}
		
		/*		Object userObj = request.getSession().getAttribute("user");
		if(userObj==null){
			request.setAttribute("msg", "��Ӷ���ǰ���ȵ�¼");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		String receiverinfo = request.getParameter("receiverinfo");
		Object cartObj = request.getSession().getAttribute("cart");
		if(cartObj==null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		Map<Product,Integer> cart = (Map<Product,Integer>)cartObj;
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setPaystate(0);
		order.setOrdertime(new Date());
		order.setReceiverinfo(receiverinfo);
		order.setUser_id(((User)userObj).getId());
		double money=0;
		List<OrderItem> oiList = new ArrayList<OrderItem>();
		for(Map.Entry<Product, Integer> entry:cart.entrySet()){
			OrderItem oi = new OrderItem();
			oi.setOrder_id(order.getId());
			oi.setProduct_id(entry.getKey().getId());
			oi.setBuynum(entry.getValue());
			oiList.add(oi);
			money+=entry.getKey().getPrice()*entry.getValue();
		}
		order.setMoney(money);
		OrderService os = BasicFactory.getFactory().getInstance(OrderService.class);
		try {
			os.addOrder(order,oiList);
			request.getSession().removeAttribute("cart");
			response.getWriter().write("������ӳɹ�!");
			response.setHeader("Refresh", "2;url="+request.getContextPath()+"/");
		} catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}*/

}
