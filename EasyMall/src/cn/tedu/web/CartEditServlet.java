package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;

public class CartEditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1043161165475695706L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1接受参数
		String id =req.getParameter("id");
		int buyNum= Integer.parseInt(req.getParameter("buynum"));
		//2从Session中获取cart
		Object obj = req.getSession().getAttribute("cart");
		//3obj=null->index.jsp
		if(obj==null){
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}else{
			//4 not null
			@SuppressWarnings("unchecked")
			Map<Product,Integer> cart =(Map<Product,Integer>)obj;
			Product prod = new Product();
			prod.setId(id);
			cart.put(prod,buyNum);
			resp.sendRedirect(req.getContextPath()+"/cart.jsp");
			
		}
	}

}
