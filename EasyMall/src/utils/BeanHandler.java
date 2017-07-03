package utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BeanHandler<T> implements ResultSetHandler<T> {
	private Class<T> type;
	public BeanHandler(Class<T> type){
		this.type = type;
	}
	/**��rs�����������-> T t
	 */
	public T handle(ResultSet rs) throws SQLException {
		//����T��Ӧ�Ķ���
		T t = null;
		if(rs.next()){
			try {
				t =  type.newInstance();
				BeanInfo binfo = Introspector.getBeanInfo(type);
				//��ȡT��Ӧ���е��������� ->��������
				PropertyDescriptor[] pds = binfo.getPropertyDescriptors();
				//��������
				for(int i = 0;i<pds.length;i++){
					//��ȡ ���Ե��� 
				    String name = pds[i].getName();
					//�ҵ����Զ�Ӧ��setXxx����Method md
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
