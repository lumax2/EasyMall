package utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanHandler<T> implements ResultSetHandler<T> {
	
	private Class type;
	
	public BeanHandler(Class clz){
		this.type = type;
	}
	
	/**
	 * 将结果集中的第一行记录转成一个Bean对象并返回, 如果没有记录返回null
	 * @throws SQLException 
	 */

	public T handle(ResultSet rs) throws SQLException {
		//创建T对应的对象
		T t = null;
		if(rs.next()){
			try {
				
				t =  (T)type.newInstance();
				
				BeanInfo binfo = Introspector.getBeanInfo(type);
				//获取T对应类中的所有属性 ->对象数组
				PropertyDescriptor[] pds = binfo.getPropertyDescriptors();
				//遍历数组
				for(int i = 0;i<pds.length;i++){
					//获取 属性的名 
				    String name = pds[i].getName();
					//找到属性对应的setXxx方法Method md
				    Method mtd = pds[i].getWriteMethod();
					//user.setUsername(rs.getString("username");
					try{
						mtd.invoke(t, rs.getObject(name));
					}catch (SQLException e) {
						continue;
					}
				}
				return t;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
