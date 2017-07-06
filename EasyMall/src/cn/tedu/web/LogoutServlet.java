package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//����session
		req.getSession().invalidate();
		
		//����Cookie,���30���Զ���¼
		Cookie autock = new Cookie("autologin",null);
		autock.setPath("/");
		autock.setMaxAge(0);
		resp.addCookie(autock);
		
		//req.getRequestDispatcher(req.getContextPath()+"/index.jsp").forward(req, resp);
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
		
	}
}
