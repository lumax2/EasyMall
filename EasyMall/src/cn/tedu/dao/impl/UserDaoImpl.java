package cn.tedu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import utils.JDBCUtils;

public class UserDaoImpl implements UserDao{
	Connection conn = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;
	

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

	public User findByUserName(String username) {		
		try {
			conn =JDBCUtils.getConnection();
			String sql = "select * from users where username=?";
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, username);
			rs = pstat.executeQuery();
			
			if(rs.next()){
				System.out.println("findByUserName:rs.next():"+rs.next());
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, pstat, rs);
		}
		return null;	
		
	}

	public int addUser(User user) {
		
			try {
				conn = JDBCUtils.getConnection();
				String sql2 = "insert into users (username,password,nickname,email) values(?,?,?,?)";
				pstat = conn.prepareStatement(sql2);

				pstat.setString(1, user.getUsername());
				pstat.setString(2, user.getPassword());
				pstat.setString(3, user.getNickname());
				pstat.setString(4, user.getEmail());

				int row = pstat.executeUpdate();
				return row;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JDBCUtils.close(conn, pstat, rs);
			}
			return 0;
			
	}
}
