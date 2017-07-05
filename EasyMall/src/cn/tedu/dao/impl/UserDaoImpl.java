package cn.tedu.dao.impl;


import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import utils.BeanHandler;
import utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	public User findByUnamePwd(String username, String pwd) {
		try {
			String sql = "select * from users where username=? and password = ? ";
			//System.out.println("username:"+username+"  pwd:"+pwd);
			
			//System.out.println("11111111111111:"+JDBCUtils.query(sql, new BeanHandler<User>(User.class), username,pwd));
			return JDBCUtils.query(sql, new BeanHandler<User>(User.class),username,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * try { String sql = "select * from users where username = ?" +
	 * " and password=?"; QueryRunner qr = new QueryRunner(JDBCUtils.getPool());
	 * return qr.query(sql, new BeanHandler<User>(User.class), username,pwd); }
	 * catch (Exception e) { e.printStackTrace(); } return null;
	 */

	// δʹ��DBUtils
	/*
	 * try { conn = JDBCUtils.getConnection(); String sql =
	 * "select * from users where username = ?" + " and password=?"; pstat =
	 * conn.prepareStatement(sql); pstat.setString(1, username);
	 * pstat.setString(2, pwd); rs = pstat.executeQuery(); if(rs.next()){ User
	 * user = new User(); user.setId(rs.getInt("id"));
	 * user.setUsername(rs.getString("username"));
	 * user.setPassword(rs.getString("password"));
	 * user.setNickname(rs.getString("nickname"));
	 * user.setEmail(rs.getString("email")); return user; } } catch (Exception
	 * e) { e.printStackTrace(); }finally{ JDBCUtils.close(conn, pstat, rs); }
	 * return null; }
	 */

	public int regist(User user) {
		return 0;
	}

	public int addUser(User user) {

		try {
			String sql = "insert into users(username,password,"
					+ "nickname,email) values(?,?,?,?)";
			return JDBCUtils.update(sql, user.getUsername(),
					user.getPassword(), user.getNickname(), user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

		// ʹ��apachejar������
		/*
		 * try { String sql = "insert into users(username,password," +
		 * "nickname,email) values(?,?,?,?)"; QueryRunner qr = new
		 * QueryRunner(JDBCUtils.getPool()); return qr.update(sql,
		 * user.getUsername(),user.getPassword(),
		 * user.getNickname(),user.getEmail()); } catch (Exception e) {
		 * e.printStackTrace(); } return 0;
		 */

		// δ��DBUtils
		/*
		 * try { conn = JDBCUtils.getConnection(); String sql =
		 * "insert into users(username,password," +
		 * "nickname,email) values(?,?,?,?)"; pstat =
		 * conn.prepareStatement(sql); pstat.setString(1, user.getUsername());
		 * pstat.setString(2, user.getPassword()); pstat.setString(3,
		 * user.getNickname()); pstat.setString(4,user.getEmail()); return
		 * pstat.executeUpdate(); } catch (Exception e) { e.printStackTrace(); }
		 * return 0;
		 */
	}

	/**
	 * �����û��������û�
	 * 
	 * @param username
	 * @return
	 */
	public User findByUserName(String username) {
		try {
			String sql = "select * from users where username = ?";
			
			return JDBCUtils.query(sql, new BeanHandler<User>(User.class),
					username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		// apache dbutil
		/*
		 * try { String sql = "select * from users where username = ?";
		 * QueryRunner qr = new QueryRunner(JDBCUtils.getPool()); return
		 * qr.query(sql, new BeanHandler<User>(User.class),username); } catch
		 * (Exception e) { e.printStackTrace(); } return null; }
		 */

	}
}

// δ��DBUtil
/*
 * try { conn = JDBCUtils.getConnection(); String sql =
 * "select * from users where username = ?"; pstat = conn.prepareStatement(sql);
 * pstat.setString(1, username); rs = pstat.executeQuery(); if(rs.next()){ User
 * user = new User(); user.setId(rs.getInt("id"));
 * user.setUsername(rs.getString("username"));
 * user.setPassword(rs.getString("password"));
 * user.setNickname(rs.getString("nickname"));
 * user.setEmail(rs.getString("email")); return user; } } catch (Exception e) {
 * e.printStackTrace(); }finally{ JDBCUtils.close(conn, pstat, rs); } return
 * null;
 */

