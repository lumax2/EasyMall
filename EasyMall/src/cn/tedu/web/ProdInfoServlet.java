package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ProdInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164673295964442668L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		//2
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		
		//3调用查询方法
		Product prod = prodService.findProdById(id);
		
		
		req.setAttribute("prod", prod);
		
		req.getRequestDispatcher("/prod_info.jsp").forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
