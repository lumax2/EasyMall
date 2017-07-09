package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;



import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {


	public static ComboPooledDataSource pool = new ComboPooledDataSource();
	
		
	
		public static DataSource getPool() {
		return pool;
	}

	
	
	 
	public static Connection getConnection() throws SQLException {

		return pool.getConnection();

	}

	
	/** 工具方法
	 * 
	 * @param conn
	 * @param pstat
	 * @param rs
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
	 * 实现CUD操作
	 * 
	 * @param sql
	 *            ：sql语句
	 * @param params
	 *            ：为占位符赋值的可变长度对象数据
	 * @return 影响的行数
	 * @throws SQLException
	 */
	public static int update(String sql, Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			pstat = conn.prepareStatement(sql);
			// 为占位符赋值
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
	 * 查询的方法，即可以查询一个对象，也可以查询List<T>
	 * 
	 * @param sql
	 *            :sql语句
	 * @param rsh
	 *            ：如果返回当个对象 new BeanHandler<T>(Class<T>) 如果返回集合对象List<T>，使用new
	 *            BeanListHandler<T>(Class<T>)
	 * @param params
	 *            :参数对象数组
	 * @return T或List<T>
	 * @throws SQLException
	 */

		/**
		 * 查询记录
		 * @param sql
		 * @param rsh
		 * @param params
		 * @return
		 * @throws Exception
		 */
		public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
				throws Exception {
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				ps = conn.prepareStatement(sql);
				if(params != null && params.length >0){
					for (int i = 0; i < params.length; i++) {
						ps.setObject(i+1, params[i]);
					}
				}
				
				rs = ps.executeQuery();
				//System.out.println("RS:"+rs);
				/*while(rs.next()){
					System.out.println("username111"+rs.getString("username"));
					System.out.println("1111"+rs.getString(2));
				}*/
				//处理结果集
				return rsh.handle(rs);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				close(conn, ps, rs);
			}
	}
}
