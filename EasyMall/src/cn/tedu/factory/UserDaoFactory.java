package cn.tedu.factory;

import cn.tedu.dao.UserDao;
import utils.PropUtils;

public class UserDaoFactory {
	private static UserDaoFactory factory = new UserDaoFactory();

	private UserDaoFactory() {
	}

	public static UserDaoFactory getFactory() {
		return factory;
	}

	public UserDao getInstance() {
		// ��ȡ�����ļ�����ȡ����������
		String className = PropUtils.getProperty("UserDao");
		try {
			Class clz = Class.forName(className);
			return (UserDao) clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
