package cn.tedu.factory;

import utils.PropUtils;

public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	private BasicFactory(){}
	public static BasicFactory getFactory(){
		return factory;
	}
	//T:UserDao
	//Class<T>:UserDao.class
	@SuppressWarnings("unchecked")
	public  <T> T getInstance(Class<T> intfClz){
		//UserDao.class->"UserDao"
		//getName()->"cn.tedu.dao.UserDao"
		//getSimpleName()->"UserDao"
		String intfName = intfClz.getSimpleName();
		//intfName:UserDao
		//��ȡUserDao��config.properties�ļ��ж�Ӧʵ����İ���.����
		String className = PropUtils.getProperty(intfName);
		try {
			Class<?> clz = (Class<T>) Class.forName(className);
			//clz��������
			return (T)clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

