package cn.tedu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import utils.JDBCUtils;

public class UserDaoImpl implements UserDao{
	
	
	private Connection conn = null;
	private PreparedStatement pstat=null;
	private ResultSet rs = null;
	
	public User findByUnamePwd(String username, String pwd) {
			/*try{
			String sql = "select * from users where username = ?" +
				" and password=?";
			QueryRunner qr = new QueryRunner(JDBCUtils.getPool());
			return qr.query(sql, new BeanHandler<User>(User.class),username,pwd);
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				JDBCUtils.close(conn, pstat, rs);
			}
				return null;
			}*/
			
			try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from users where username = ?" +
			" and password=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			pstat.setString(2, pwd);
			rs = pstat.executeQuery();
			if(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setEmail(rs.getString("email"));
			return user;
			}
			} catch (Exception e) {
			e.printStackTrace();
			}finally{
			JDBCUtils.close(conn, pstat, rs);
			}
			return null;
	}	

	public int regist(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User login(String username, String password) {
	
		
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from users where username=? and password=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			pstat.setString(2, password);
			rs = pstat.executeQuery();
			
			if(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				return user;
			}
				System.out.println(rs.next());
				return null;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(conn, pstat, rs);
		}

	}

	public int addUser(User user) {
			
		/*	try {
				String sql = "select * from users where username=? and password=?";
				return JDBCUtils.update(sql, user.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;*/
			
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into users(username,password," +
			"nickname,email) values(?,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getNickname());
			pstat.setString(4,user.getEmail());
			return pstat.executeUpdate();
			} catch (Exception e) {
			e.printStackTrace();
			}
			return 0;
	}

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User findByUserName(String username) {
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from users where username = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, username);
			rs = pstat.executeQuery();
			if(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setEmail(rs.getString("email"));
			return user;
			}
			} catch (Exception e) {
			e.printStackTrace();
			}finally{
				JDBCUtils.close(conn, pstat, rs);
			}
			return null;
	}

	
}
