package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.entity.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

import utils.WebUtils;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//1、设置接收参数的编码格式utf-8
		//req.setCharacterEncoding("utf-8");
		//2、接收用户名和密码
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();
		
		//3、非空验证
		boolean isTrue=true;
		
		if(WebUtils.check(username)){
		req.setAttribute("username_msg", "用户名不能为空fw");
		isTrue = false;
		}
		if(WebUtils.check(password)){
		req.setAttribute("password_msg", "密码不能为空fw");
		isTrue = false;
		}
		
		if(!isTrue){
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
		return;
		}
		
		
		try {
			
			//A1.创建业务层对象
			UserService userService = BasicFactory.getFactory().getInstance(UserService.class);
			//A2.在对应的包下创建业务层的接口和实现类
			//A3.调用业务层的方法
			User user = userService.login(username,password);
			//A4.创建User，添加login(..)方法
			//System.out.println("user"+user);
			//5判断用户是否存在
			if(user!=null){
				//7存在,将用户名保存到session中
				if("true".equals(req.getParameter("remname"))){
				Cookie cookie = new Cookie("remname",URLEncoder.encode(username, "utf-8"));
				cookie.setPath("/");
				cookie.setMaxAge(3600*24*30);
				resp.addCookie(cookie);
				}
				
				//处理30天自动登录
				if("true".equals(req.getParameter("autologin"))){
					Cookie autock = new Cookie("autologin",URLEncoder.encode((username+":"+password),"utf-8"));
					autock.setPath("/");
					autock.setMaxAge(3600*24*30);
					resp.addCookie(autock);
				}
				
				req.getSession().setAttribute("user", user);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}else{
				//6不存在，给予提示跳转到login.jsp
				req.setAttribute("msg", "用户名或密码错误fw");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "系统错误，请重试！fw");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
	}
}
		
		/*//4 根据JDBC 查询用户
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
	}*/

