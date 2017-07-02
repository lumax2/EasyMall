package cn.tedu.service;

import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;

public interface UserService {

	public User loginService(String username,String password);
	/**
	 * �ж��û����Ƿ����
	 * @param username
	 * @return true:�û�����  false;�û�������
	 */
	public boolean isExist(String username);
	
	public void regist(User user)throws MsgException;
}
