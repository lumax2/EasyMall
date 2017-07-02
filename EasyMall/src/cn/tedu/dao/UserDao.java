package cn.tedu.dao;

import cn.tedu.entity.User;

public interface UserDao {
	

	/**
	 * 注册用户
	 * @param user 封装了用户信息user类对象
	 * @return影响行数
	 */
	public int regist(User user);
	/**
	 * 登陆：根据用户名和密码
	 * @param username 用户名	
	 * @param password 密码
	 * @return 存在则返回 封装了用户信息的User的类对象，不存在则返回null
	 */
	public User login(String username,String password);
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
