package utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TranManager {
	
	private  static ThreadLocal<Connection> t1= new ThreadLocal<Connection>(){
		
		protected Connection initialValue() {
			try {
				return JDBCUtils.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
		};
	};
	//private static Connection conn =null;
	/*static{
		try {
		Connection	conn=t1.set(JDBCUtils.getConnection());
		} catch (SQLException e) {                  
			e.printStackTrace();
		}
	}*/

	/**
	 * 获取数据库连接的方法
	 * @return
	 */
	public static Connection getConn(){
		return t1.get();
	}
	
	public static void startTran(){
		
		try {
			t1.get().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commitTran(){
		try {
			t1.get().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollbackTran(){
		try {
			t1.get().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void release(){
		try {
			t1.get().close();
			t1.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
