package cn.tedu.web.back;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class BackProdDeleteServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		String id = req.getParameter("id");
		
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
				
		boolean result = prodService.deleteProdById(id);
		
		if(result){
			resp.getWriter().write("É¾³ý³É¹¦");
		}else{
			resp.getWriter().write("É¾³ýÊ§°Ü");
		}
		resp.setHeader("Refresh","3;url=${app}/BackProdListServlet");
	}
}
