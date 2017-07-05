package cn.tedu.dao;

import cn.tedu.entity.User;

public interface UserDao {
	

	/**根据用户名和密码查询对应的用户
	* @param username:用户名
	* @param pwd：密码
	* @return 存在则返回封装信息User类对象，反之返回null
	*/
	public User findByUnamePwd(String username, String pwd);
	
	
	/**
	 * 注册用户
	 * @param user 封装了用户信息user类对象
	 * @return影响行数
	 */
	public int regist(User user);
	
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User findByUserName(String username);

	/**
	 * 向users表中添加一个用户信息
	 * @param user：封装了信息 user对象
	 * return 影响行数
	 */
	public int addUser(User user);
	
	
	
}
