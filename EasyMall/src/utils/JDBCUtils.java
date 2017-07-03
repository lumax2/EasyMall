package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;

import cn.tedu.entity.User;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	public static ComboPooledDataSource pool = new ComboPooledDataSource();
	
	@Test
	public void test1() {
		String sql = "select * from users";
		List<User> list;
		try {
			list = JDBCUtils.query(sql, new BeanListHandler<User>(User.class));
			for (User user : list) {
				System.out.println(user.getId() + "," + user.getUsername());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from users";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("nickname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, pstat, rs);
		}

	}

	/*
	 * ��ȡ���ӳ�ʵ��
	 */
	public static DataSource getPool() {
		return pool;
	}

	/*
	 * �����ӳ��л�ȡһ������
	 */
	public static Connection getConnection() throws SQLException {

		return pool.getConnection();

	}

	/*
	 * ���߷���
	 */
	public static void close(Connection conn, PreparedStatement pstat,
			ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
		if (pstat != null) {
			try {
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pstat = null;
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}

	}

	/**
	 * ʵ��CUD����
	 * 
	 * @param sql
	 *            ��sql���
	 * @param params
	 *            ��Ϊռλ����ֵ�Ŀɱ䳤�ȶ�������
	 * @return Ӱ�������
	 * @throws SQLException
	 */
	public static int update(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			pstat = conn.prepareStatement(sql);
			// Ϊռλ����ֵ
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstat.setObject(i + 1, params[i]);
				}
			}
			return pstat.executeUpdate();
		} catch (SQLException se) {
			throw se;
		} finally {
			close(conn, pstat, null);
		}
	}

	/**
	 * ��ѯ�ķ����������Բ�ѯһ������Ҳ���Բ�ѯList<T>
	 * 
	 * @param sql
	 *            :sql���
	 * @param rsh
	 *            ��������ص������� new BeanHandler<T>(Class<T>) ������ؼ��϶���List<T>��ʹ��new
	 *            BeanListHandler<T>(Class<T>)
	 * @param params
	 *            :������������
	 * @return T��List<T>
	 * @throws SQLException
	 */
	/**
	 * ��ѯ�ķ����������Բ�ѯһ������Ҳ���Բ�ѯList<T>
	 * 
	 * @param sql
	 *            :sql���
	 * @param rsh
	 *            ��������ص������� new BeanHandler<T>(Class<T>) ������ؼ��϶���List<T>��ʹ��new
	 *            BeanListHandler<T>(Class<T>)
	 * @param params
	 *            :������������
	 * @return T��List<T>
	 * @throws SQLException
	 */
	public static <T> T query(String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstat = conn.prepareStatement(sql);
			// Ϊռλ����ֵ
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstat.setObject(i + 1, params[i]);
				}
			}
			rs = pstat.executeQuery();
			return rsh.handle(rs);
		} catch (SQLException e) {
			throw e;
		} finally {
			close(conn, pstat, rs);
		}

	}

	
}
