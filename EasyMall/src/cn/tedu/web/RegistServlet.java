package cn.tedu.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.sun.org.apache.commons.beanutils.BeanUtils;

import cn.tedu.dao.UserDao;
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
			// TODO Auto-generated catch block
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

		// 1���ý��ܲ������ַ��������ʽ ����
		req.setCharacterEncoding("utf-8");

			//������ظ��ύ2
			Object tk1obj = req.getSession().getAttribute("token");
				//���������н���token
			String tk2 = req.getParameter("token");
			if(tk2==null || tk1obj==null ||!tk2.equals(tk1obj)){
				//��ʾ�ظ��ύ
				throw new RuntimeException("�벻Ҫ�ظ��ύ��");
			}else{
				//��ʾ��һ���ύ
				req.getSession().removeAttribute("token");
			}
		
		
			
		// 2 ���ղ���
		
		
		
		String valistr = req.getParameter("valistr");
		//��֤���жϣ�
		//1
		Object codeObj = req.getSession().getAttribute("code");
		//2�ж���֤���Ƿ�Ϊ��
		if (WebUtils.check(valistr)) {
			req.setAttribute("valistr_msg", "��֤�벻��Ϊ��");
			/*req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return;*/
		}
		//3��֤����ͬ
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
			// TODO Auto-generated catch block
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
						"ע��ɹ���3����Զ���ת����û����ת��<a href='" + req.getContextPath()
								+ "/index.jsp'>�������</a>");
			}else{
				req.setAttribute("un_msg", "ϵͳ����������ע��");
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				return;
			}

		}
	}



/*		//1�����ý��ղ������ַ��������ʽ
		req.setCharacterEncoding("utf-8");
		//2�����ղ���
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2=req.getParameter("password2");
		String nickname =req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr=req.getParameter("valistr");
		//3����֤�����Ƿ�Ϸ�
		if(WebUtils.check(username)){
			//���Ϸ���������ʾ������ת��regist.jsp
			req.setAttribute("msg", "�������û�����");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			return;
		}
		if(WebUtils.check(password)){
			req.setAttribute("msg", "���벻��Ϊ��!");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			//ctrl+shift+enter���ڹ�������е���һ�в�������
			//shift+enter���ڹ�������е���һ�в�������
			return;
		}
		if(!password.equals(password2)){
			req.setAttribute("msg", "�������벻��ͬ��");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			return;
		}
		if(WebUtils.check(nickname)){
			req.setAttribute("msg", "�ǳƲ���Ϊ�գ�");
			req.getRequestDispatcher("/regist.jsp").
				forward(req,resp);
			return;
		}
		if(WebUtils.check(email)){
			req.setAttribute("msg", "���䲻��Ϊ��!");
			req.getRequestDispatcher("/regist.jsp").
				forward(req, resp);
			return;
		}
		Connection conn = null;
		PreparedStatement pstat= null;
		ResultSet rs = null;
		try {
			//4���ж��û����Ƿ����
			conn= JDBCUtils.getConnection();
			//4.1����дsql��� 
			String sql = "select * from users" +
					" where username=?";
			//4.2��Ԥ����sql��䣬������pstat
			pstat = conn.prepareStatement(sql);
			//4.3��Ϊռλ����ֵ
			pstat.setString(1, username);
			//4.4��ִ�в�ѯ����
			rs = pstat.executeQuery();
			//4.5���ж��û����Ƿ����
			if(rs.next()){//������
				req.setAttribute("msg", "�û����Ѿ�����");
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			}else{//5������
				//5.1��дsql���
				String sql2 = "insert into users(username,password,nickname,email)" +
						" values(?,?,?,?)";
				//5.2Ԥ����sql��䣬������pstat
				pstat = conn.prepareStatement(sql2);
				//5.3Ϊռλ����ֵ
				pstat.setString(1, username);
				pstat.setString(2,password);
				pstat.setString(3, nickname);
				pstat.setString(4, email);
				//6.ִ����Ӳ���,������Ӱ�����
				int row= pstat.executeUpdate();
				//7.�ж�ע��ɹ�����ʧ��
				if(row>0){//�ɹ�
					resp.setContentType("text/html;charset=utf-8");
					resp.setHeader("refresh", "3;url="+req.getContextPath()+"/index.jsp");
					resp.getWriter().write("3����Զ���ת");
				}else{//ʧ��
					req.setAttribute("msg", "ϵͳ����������ע��");
					req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//8�����ң��ر����ݿ�
			JDBCUtils.close(conn, pstat, rs);
		}
		*/

