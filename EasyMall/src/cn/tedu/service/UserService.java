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
	
	/**注册用户的方法
	* @param user：封装了用户信息的User类的对象
	* 抛出了Exception异常而不是MsgException也可行，为什么？
	*/
	public void regist(User user) throws Exception;
	
	
	/**登录方法
	* @param username:用户名
	* @param pwd：密码
	* @return 成功返回User对象，失败返回null
	*/
	public User login(String username, String pwd);
	
}
