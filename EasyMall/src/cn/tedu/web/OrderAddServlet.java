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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object userObj = request.getSession().getAttribute("user");
		if(userObj==null){
			request.setAttribute("msg", "添加订单前请先登录");
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
			response.getWriter().write("订单添加成功!");
			response.setHeader("Refresh", "2;url="+request.getContextPath()+"/");
		} catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}

}
