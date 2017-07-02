package cn.tedu.service;

import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;

public interface UserService {

	public User loginService(String username,String password);
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return true:用户存在  false;用户不存在
	 */
	public boolean isExist(String username);
	
	public void regist(User user)throws MsgException;
}
