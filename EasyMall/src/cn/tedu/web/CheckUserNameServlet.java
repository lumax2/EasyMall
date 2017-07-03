package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;

import utils.JDBCUtils;

public class CheckUserNameServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// System.out.println("post");
		resp.setContentType("html/text;charset=utf-8");

		// 1接收参数
		String username = req.getParameter("username");// 1、接收参数
		// 2、判断用户名在数据库中是否存在

		try {
			UserService userService = BasicFactory.getInstance(
					UserService.class);
			
			resp.getWriter().write(userService.isExist(username) + "");

		} catch (Exception e) {
			e.printStackTrace();
			// response.getWriter().write("系统错误,请重试!");
			resp.getWriter().write("error");
		}
	}

}
/*
 * Connection conn=null; PreparedStatement pstat = null; ResultSet rs = null;
 */

/*
 * try { conn=JDBCUtils.getConnection(); String sql
 * ="select * from users where username=?"; pstat = conn.prepareStatement(sql);
 * pstat.setString(1, username); rs = pstat.executeQuery();
 */
// UserDao ub =BasicFactory.getFactory.getInstance(UserDao.class);
// UserService userService = new UserServiceImpl();

// UserDao userDao = new UserDaoImpl();
/*
 * if(userService.isExist(username)){ //System.out.println(rs.getString(2));
 * //resp.getWriter().write("用户名已存在！！"); resp.getWriter().write("true"); }else{
 * //resp.getWriter().write("<font color='red'>用户名可用！！</font>");
 * resp.getWriter().write("false");
 * 
 * }
 */

/*
 * } catch (SQLException e) { e.printStackTrace();
 * //resp.getWriter().write("系统错误，请重试！！"); resp.getWriter().write("error");
 * }finally{ JDBCUtils.close(conn, pstat, rs); }
 */

