package utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BeanListHandler<T> implements ResultSetHandler<List<T>> {
	private Class type;

	public BeanListHandler(Class type) {
		this.type = type;
	}
	
	public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list = new ArrayList<T>();
		if (rs != null) {
			while (rs.next()) {
				try {
					T t = (T)type.newInstance();
					BeanInfo binfo = Introspector.getBeanInfo(type);
					PropertyDescriptor[] pds = binfo.getPropertyDescriptors();
					for (int i = 0; i < pds.length; i++) {
						// ��ȡ��������--�����ֶ�����
						String name = pds[i].getName();
						// ��ȡsetXxx(..)
						Method mtd = pds[i].getWriteMethod();
						try {
							mtd.invoke(t, rs.getObject(name));
						} catch (SQLException e) {
							continue;
						}
					}
					list.add(t);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}