package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ProdListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//1
				String nameStr = req.getParameter("name");
				String cateStr = req.getParameter("category");
				String minpriceStr = req.getParameter("minprice");
				String maxpriceStr = req.getParameter("maxprice");
				//2.1
				String name = "";
				String cate = "";
				Double min = null;
				Double max = null;
				//2.2
				if(nameStr!=null&&!"".equals(nameStr.trim())){
					name = nameStr.trim();
				}
				if(cateStr != null && !"".equals(cateStr.trim())){
					cate = cateStr.trim();
				}
				if(minpriceStr != null && !"".equals(minpriceStr.trim())){
					try {
						min = Double.parseDouble(minpriceStr);
					} catch (NumberFormatException e) {
						
					}
				}
				if(maxpriceStr != null && !"".equals(maxpriceStr.trim())){
					try {
						max = Double.parseDouble(maxpriceStr);
					} catch (NumberFormatException e) {
					}
				}
				//3
				ProdService ps = BasicFactory.getFactory().getInstance(ProdService.class);
				System.out.println("name"+name+";cate"+cate+";min:"+min+";max:"+max);
				//4 调用查询的方法
				List<Product> list = ps.findAllByKey(name,cate,min,max);
				
				//5、将结果集和查询条件保存到request域
				req.setAttribute("list", list);
				req.setAttribute("name", name);
				req.setAttribute("cate",cate);
				req.setAttribute("min", min);
				req.setAttribute("max", max);
				
				//6
				req.getRequestDispatcher("/prod_list.jsp").forward(req,resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doGet(req,resp);
		
	}
}
