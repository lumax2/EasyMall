package cn.tedu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class CartAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573886898535764900L;

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1
		String id = req.getParameter("id");
		//2
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		//3
		Product prod = prodService.findProdById(id);
		//4
		Object obj = req.getSession().getAttribute("cart");
		//5
		Map<Product,Integer> cart =null;
		//6
		if(obj==null){
			//6.1.1
			cart = new HashMap<Product,Integer>();
			//6.1.2
			req.getSession().setAttribute("cart", cart);
		}else{
			cart = (Map<Product,Integer>)obj;
		}
		//7
		//7.1.1
		if(cart.containsKey(prod)){
			cart.put(prod, cart.get(prod)+1);
		}else{
			//Ö®Ç°Î´¹ºÂò¹ý
			cart.put(prod, 1);
		}
		//8
		resp.sendRedirect(req.getContextPath()+"/cart.jsp");
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}

}
