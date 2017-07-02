package cn.tedu.factory;

import java.io.FileInputStream;
import java.util.Properties;

import utils.PropUtils;

import cn.tedu.service.UserService;

public class UserServiceFactory {

	private static UserServiceFactory factory = new UserServiceFactory();
	private UserServiceFactory(){};
	
	public static UserServiceFactory getFactory(){
		return factory;
	}
	public UserService getInstance(){
		
	String className=PropUtils.getProperty("UserService");
		
		try {
			Class clz = Class.forName(className);
			return (UserService)clz.newInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
