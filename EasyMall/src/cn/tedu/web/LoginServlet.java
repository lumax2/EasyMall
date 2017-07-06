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
		
		//1�����ý��ղ����ı����ʽutf-8
		//req.setCharacterEncoding("utf-8");
		//2�������û���������
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();
		
		//3���ǿ���֤
		boolean isTrue=true;
		
		if(WebUtils.check(username)){
		req.setAttribute("username_msg", "�û�������Ϊ��fw");
		isTrue = false;
		}
		if(WebUtils.check(password)){
		req.setAttribute("password_msg", "���벻��Ϊ��fw");
		isTrue = false;
		}
		
		if(!isTrue){
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
		return;
		}
		
		
		try {
			
			//A1.����ҵ������
			UserService userService = BasicFactory.getFactory().getInstance(UserService.class);
			//A2.�ڶ�Ӧ�İ��´���ҵ���Ľӿں�ʵ����
			//A3.����ҵ���ķ���
			User user = userService.login(username,password);
			//A4.����User�����login(..)����
			//System.out.println("user"+user);
			//5�ж��û��Ƿ����
			if(user!=null){
				//7����,���û������浽session��
				if("true".equals(req.getParameter("remname"))){
				Cookie cookie = new Cookie("remname",URLEncoder.encode(username, "utf-8"));
				cookie.setPath("/");
				cookie.setMaxAge(3600*24*30);
				resp.addCookie(cookie);
				}
				
				//����30���Զ���¼
				if("true".equals(req.getParameter("autologin"))){
					Cookie autock = new Cookie("autologin",URLEncoder.encode((username+":"+password),"utf-8"));
					autock.setPath("/");
					autock.setMaxAge(3600*24*30);
					resp.addCookie(autock);
				}
				
				req.getSession().setAttribute("user", user);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}else{
				//6�����ڣ�������ʾ��ת��login.jsp
				req.setAttribute("msg", "�û������������fw");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "ϵͳ���������ԣ�fw");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
	}
}
		
		/*//4 ����JDBC ��ѯ�û�
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
				//�����ڽ��û�������session��
				req.getSession().setAttribute("username", username);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
				
			}else{//�����ڣ���ת��login.jsp ��������ʾ
				req.setAttribute("msg", "�û������������");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "ϵͳ����������");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}*/

