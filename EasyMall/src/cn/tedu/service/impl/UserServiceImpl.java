package cn.tedu.service.impl;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.impl.UserDaoImpl;
import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class UserServiceImpl implements UserService{
	
	
	private  UserDao userDao = BasicFactory.getInstance(UserDao.class);
	/**
	 * 登陆办法
	 */
	public User loginService(String username, String password) {
		
	User user =	userDao.login(username, password);
		return user;
	}
	
	public boolean isExist(String username) {
		User user = userDao.findByUserName(username);
		return user != null;
	}

	public void regist(User user) throws MsgException {
		
			
		//判断用户名是否可用
		User result=userDao.findByUserName(user.getUsername());
		if(result !=null){
			throw new MsgException("用户名已存在");
		}
		//
		userDao.addUser(user);
			
		}

}
