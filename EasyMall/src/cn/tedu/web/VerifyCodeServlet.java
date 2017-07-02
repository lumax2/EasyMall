package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		VerifyCode vc =new VerifyCode();
		
		vc.drawImage(resp.getOutputStream());
		//将来保存到session中，以便注册时servlet时验证
		
			//用session实现验证码
		String code = vc.getCode();
		req.getSession().setAttribute("code", code);
			//接下来修改RegistServlet
		
		System.out.println(code);
		
	
	}
}
