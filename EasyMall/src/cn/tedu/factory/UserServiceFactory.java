package cn.tedu.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import utils.PropUtils;

import cn.tedu.service.UserService;

public class UserServiceFactory {

	private static UserServiceFactory factory=new UserServiceFactory();
	private UserServiceFactory(){}
	public static UserServiceFactory getFactory(){
	return factory;
	}
	public UserService getInstance() {
	Properties prop = new Properties();
	try {
		prop.load(new FileInputStream(new File("config.properties")));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
	}
}
