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

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;


import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.impl.UserServiceImpl;

import utils.JDBCUtils;
import utils.WebUtils;

public class RegistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Test
	public void test(){
		 Connection conn = null;
		 PreparedStatement pstat = null;
		 ResultSet rs = null;
		 try {
			conn =JDBCUtils.getConnection();
			String sql = "select * from users";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("nickname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, pstat, rs);
		}
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//1、设置接收参数的字符集编码格式
		req.setCharacterEncoding("utf-8");
		//A1.从session中获取令牌
		Object tk1Obj = req.getSession().getAttribute("token");
		//A2、从隐藏域中接收token
		String tk2 = req.getParameter("token");
		//A3、比较
		if(tk2==null||tk1Obj==null||!tk2.equals((String)tk1Obj)){
		//重复提交
		throw new RuntimeException("请不要重复提交(fw)");
		}else{//第一次提交
		//从session中删除令牌
		req.getSession().removeAttribute("token");
		}
		//2、接收参数
		String valistr=req.getParameter("valistr");
		//valistr的判断
		//B1.从session中获取验证码
		Object codeObj = req.getSession().getAttribute("code");
		//B2.判断输入框中的验证码是否为空
		if(WebUtils.check(valistr)){
		req.setAttribute("valistr_msg","验证码不能为空(fw)");
		req.getRequestDispatcher("/regist.jsp").forward(req, resp);
		return;
		}
		//B3、验证两个是否相同
		if(!(codeObj!=null&&valistr.equals((String)codeObj))){
		req.setAttribute("valistr_msg", "验证码输入错误(fw)");
		req.getRequestDispatcher("/regist.jsp").forward(req, resp);
		return;
		}
		try {
		//C1、创建User对象
		User user = new User();
		//C2、将form表单的信息封装到user中
		BeanUtils.populate(user, req.getParameterMap());
		//C3、调用数据验证的方法
		user.check();
		//C4、创建业务层对象
		UserService userService = BasicFactory.getInstance(UserService.class);
	/*	UserService userService = new UserServiceImpl();*/
		//C5、调用注册的方法
		userService.regist(user);
		//注册成功
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}catch(MsgException me){
		req.setAttribute("msg", me.getMessage());
		req.getRequestDispatcher("/regist.jsp").forward(req, resp);
		} catch (Exception e) {
		e.printStackTrace();
		req.setAttribute("msg", "系统错误");
		req.getRequestDispatcher("/regist.jsp").forward(req, resp);
		}

	}
}
		
		/*// 1设置接受参数的字符集编码格式 令牌
		req.setCharacterEncoding("utf-8");

			//避免表单重复提交2
			Object tk1obj = req.getSession().getAttribute("token");
				//从隐藏域中接受token
			String tk2 = req.getParameter("token");
			if(tk2==null || tk1obj==null ||!tk2.equals(tk1obj)){
				//表示重复提交
				throw new RuntimeException("请不要重复提交！");
			}else{
				//表示第一次提交
				req.getSession().removeAttribute("token");
			}
		
		
			
		// 2 接收参数
		
		
		
		String valistr = req.getParameter("valistr");
		//验证码判断：
		//1
		Object codeObj = req.getSession().getAttribute("code");
		//2判断验证码是否为空
		if (WebUtils.check(valistr)) {
			req.setAttribute("valistr_msg", "验证码不能为空");
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;
		}
		//3验证不相同
		User user= new User();
		
		try {
			BeanUtils.populate(user, req.getParameterMap());
			user.check();
			UserService userService = BasicFactory.getInstance(UserService.class);
			userService.regist(user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (MsgException e) {
			e.printStackTrace();
		}
		
		
		
		
		UserDao userDao = BasicFactory.getInstance(UserDao.class);
		
		
		int row =userDao.addUser(user);
	
			//System.out.println("11111111111111111111111111111");

			if (row > 0) {
				resp.setContentType("text/html;charset=utf-8");
				resp.setHeader("refresh", "3;url=" + req.getContextPath()
						+ "/index.jsp");
				
				resp.getWriter().write(
						"注册成功，3秒后自动跳转，如没有跳转，<a href='" + req.getContextPath()
								+ "/index.jsp'>请点这里</a>");
			}else{
				req.setAttribute("un_msg", "系统错误，请重新注册");
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				return;
			}

		}
	}
*/


/*		//1、设置接收参数的字符集编码格式
		req.setCharacterEncoding("utf-8");
		//2、接收参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2=req.getParameter("password2");
		String nickname =req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr=req.getParameter("valistr");
		//3、验证参数是否合法
		if(WebUtils.check(username)){
			//不合法，给予提示，并跳转到regist.jsp
			req.setAttribute("msg", "请输入用户名！");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			return;
		}
		if(WebUtils.check(password)){
			req.setAttribute("msg", "密码不能为空!");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			//ctrl+shift+enter：在光标所在行的上一行插入新行
			//shift+enter：在光标所在行的下一行插入新行
			return;
		}
		if(!password.equals(password2)){
			req.setAttribute("msg", "两次密码不相同！");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			return;
		}
		if(WebUtils.check(nickname)){
			req.setAttribute("msg", "昵称不能为空！");
			req.getRequestDispatcher("/regist.jsp").
				forward(req,resp);
			return;
		}
		if(WebUtils.check(email)){
			req.setAttribute("msg", "邮箱不能为空!");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			return;
		}
		Connection conn = null;
		PreparedStatement pstat= null;
		ResultSet rs = null;
		try {
			//4、判断用户名是否可用
			conn= JDBCUtils.getConnection();
			//4.1、编写sql语句 
			String sql = "select * from users" +
					" where username=?";
			//4.2、预编译sql语句，并返回pstat
			pstat = conn.prepareStatement(sql);
			//4.3、为占位符赋值
			pstat.setString(1, username);
			//4.4、执行查询操作
			rs = pstat.executeQuery();
			//4.5、判断用户名是否可用
			if(rs.next()){//不可用
				req.setAttribute("msg", "用户名已经存在");
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			}else{//5、可用
				//5.1编写sql语句
				String sql2 = "insert into users(username,password,nickname,email)" +
						" values(?,?,?,?)";
				//5.2预编译sql语句，并返回pstat
				pstat = conn.prepareStatement(sql2);
				//5.3为占位符赋值
				pstat.setString(1, username);
				pstat.setString(2,password);
				pstat.setString(3, nickname);
				pstat.setString(4, email);
				//6.执行添加操作,并返回影响的行
				int row= pstat.executeUpdate();
				//7.判断注册成功还是失败
				if(row>0){//成功
					resp.setContentType("text/html;charset=utf-8");
					resp.setHeader("refresh", "3;url="+req.getContextPath()+"/index.jsp");
					resp.getWriter().write("3秒后自动跳转");
				}else{//失败
					req.setAttribute("msg", "系统错误，请重新注册");
					req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//8勿忘我：关闭数据库
			JDBCUtils.close(conn, pstat, rs);
		}
		*/

