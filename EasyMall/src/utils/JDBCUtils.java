package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	/**
	 * 使用C3P0连接池
	 */
	private  static ComboPooledDataSource pool =new ComboPooledDataSource();
	
	/**
	 * 获取程序中的连接池实例
	 * @return DataSource
	 */
	public static DataSource getPool(){
		return pool;
	}

	
	/**
	 * 从连接池中获取一个连接实例
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws SQLException {
		
		Connection conn = pool.getConnection();
		return conn;
	}
	
	/**
	 * 工具方法: 释放资源
	 * @param conn 连接对象
	 * @param stat 传输器对象
	 * @param rs 结果集对象
	 */
	public static void close(Connection conn,PreparedStatement pstat,ResultSet rs){
		if(conn!= null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn =null;
			}
		}
		if(pstat!= null){
			try {
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				pstat =null;
			}
		}
		if(rs!= null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs =null;
			}
		}
		
	}
	/**
	 * 实现CUD操作
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int update(String sql,Object...params)throws SQLException{
		Connection conn =null;
		PreparedStatement pstat= null;
		
		try{
			conn=getConnection();
			pstat = conn.prepareStatement(sql);
			//为占位符赋值
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstat.setObject(i+1,params[i]);
				}
				return pstat.executeUpdate();
			}
			
		}catch(SQLException e){
			throw new SQLException();
		}finally{
			close(conn, pstat, null);
		}
		return 0;
		
	}
	public <T>T query(String sql,ResultSetHandler<T> rsh,Object...params){
		Connection conn = null;
		PreparedStatement pstat=null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			pstat = conn.prepareStatement(sql);
			if(params !=null){
				for(int i=0;i<params.length;i++){
					pstat.setObject(parameterIndex, x)
				}
			}
			
		}catch(SQLException e){
			throw e;
			
		}finally{
			JDBCUtils.close(conn, pstat, rs);
		}
		return null;
	}
	
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
	

}
