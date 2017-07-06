package cn.tedu.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class BackProdListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		//1
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		
		List<Product> list = prodService.findAll();
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/back/prod_list.jsp").forward(req, resp);
	}
}
