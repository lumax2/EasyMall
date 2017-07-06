package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxChangePnumServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1接受参数
		String id = req.getParameter("id");
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		
		//2
		ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
		
		//3调用业务层修改库存的方法
		boolean result = prodService.changePnum(id,pnum);
		System.out.println("ajaxchangePnumS  result:"+result);
		resp.getWriter().write(result+"");
		
		
	}

}
