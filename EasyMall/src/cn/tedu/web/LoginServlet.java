package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.User;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;

import utils.WebUtils;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean isTrue = true;
		if(WebUtils.check(username)){
			req.setAttribute("username_msg", "用户名不能为空");
			isTrue =false;
		}
		if(WebUtils.check(password)){
			req.setAttribute("password_msg", "密码不能为空");
			isTrue = false;
		}
		
		if(!isTrue){
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
		//4 根据JDBC 查询用户
		try{
		UserService userService = new UserServiceImpl();
		System.out.println("LoginServlet username"+username+"password:"+password);
		User user = userService.loginService(username,password);

			if(user!=null){
				//cookie
				System.out.println(req.getParameter("remname"));
				if("true".equals(req.getParameter("remname"))){
					Cookie cookie = new Cookie("remname",username);
					cookie.setPath("/");
					cookie.setMaxAge(3600*24*1);
					resp.addCookie(cookie);
				}
				//若存在将用户名存在session中
				req.getSession().setAttribute("username", username);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
				
			}else{//不存在，跳转到login.jsp ，给予提示
				req.setAttribute("msg", "用户名或密码错误");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "系统错误，请重试");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
