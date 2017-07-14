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

		
		//1、从session中获取用户信息
		Object userObj = request.getSession().
					getAttribute("user");
		if(userObj==null){//未登录
			request.getRequestDispatcher("/login.jsp").
				forward(request, response);
			return;
		}
		//2、已登录,强制类型转换并获取用户的id
		int user_id = ((User)userObj).getId();
		//3、从session中获取cart
		Object cartObj = request.getSession().
				getAttribute("cart");
		if(cartObj==null){//session失效
			response.sendRedirect(request.
					getContextPath()+"/index.jsp");
			return;
		}
		//4、不为null，强制类型转换
		Map<Product,Integer> cart = (Map<Product,Integer>)cartObj;
		//5、cart->Order order,List<OrderItem> oiList
		//5.1定义order,oiList
		Order order = new Order();
		List<OrderItem> oiList = new ArrayList<OrderItem>();
		//5.2为order赋值
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());//?
		order.setPaystate(0);//0:表示未支付
		order.setUser_id(user_id);
		order.setReceiverinfo(request.getParameter("receiverinfo"));
		//5.3定义订单金额
		double money = 0;
		//5.5遍历
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			OrderItem item = new OrderItem();
			item.setOrder_id(order.getId());
			item.setProduct_id(entry.getKey().getId());
			item.setBuynum(entry.getValue());
			//计算金额
			money+= entry.getKey().getPrice()*entry.getValue();
			//勿忘我。。。千万不要忘记将item添加到oiList
			oiList.add(item);
		}
		//5.4赋值
		order.setMoney(money);
		//6.调用业务方法
		OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
		try{
			orderService.addOrder(order, oiList);
			//7添加成功
			response.getWriter().write("订单添加成功！");
			response.setHeader("Refresh", "3;url="+request.getContextPath()+"/servlet/OrderListServlet");
		}catch (MsgException e) {
			//8添加失败
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}
		
		/*		Object userObj = request.getSession().getAttribute("user");
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
	}*/

}
