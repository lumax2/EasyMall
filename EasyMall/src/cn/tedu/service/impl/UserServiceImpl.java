package cn.tedu.service.impl;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class UserServiceImpl implements UserService{
	
	
	
	
	private  UserDao userDao = BasicFactory.getFactory().getInstance(UserDao.class);
	
	
	
	public boolean isExist(String username) {
		User user = userDao.findByUserName(username);
		return user != null;
	}
	
	
	public User login(String username, String pwd) {
		return userDao.findByUnamePwd(username,pwd);
	}
	
	
	
	public void regist(User user) throws Exception {
		//判断用户名是否可用
		User result = userDao.findByUserName(user.getUsername());
		if(result!=null){
		throw new MsgException("用户名已存在!（fw）");
		}
		//用户不存在
		userDao.addUser(user);
		}
		

}
