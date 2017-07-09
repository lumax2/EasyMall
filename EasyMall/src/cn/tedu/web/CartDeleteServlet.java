package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;

public class CartDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		Object obj = req.getSession().getAttribute("cart");
		
		if(obj==null){
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}else{
			@SuppressWarnings("unchecked")
			Map<Product,Integer> cart =(Map<Product,Integer>)obj;
			
			Product prod = new Product();
			prod.setId(id);
			
			cart.remove(prod);
			
			resp.sendRedirect(req.getContextPath()+"/cart.jsp");
		}
	}
}
