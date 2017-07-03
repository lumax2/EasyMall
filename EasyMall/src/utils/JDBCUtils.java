package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
		
	public static ComboPooledDataSource pool =new ComboPooledDataSource();
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
	/*
	 * ��ȡ���ӳ�ʵ��
	 */
	public static DataSource getPool(){
		return pool;
	}
	
	/*
	 * �����ӳ��л�ȡһ������
	 */
	public static Connection getConnection() throws SQLException {
		
		Connection conn = pool.getConnection();
	
		return conn;
	}
	/*
	 * ���߷���
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
	

}
