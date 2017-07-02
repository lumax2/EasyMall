package cn.tedu.entity;

import cn.tedu.exception.MsgException;
import utils.WebUtils;

public class User {
	/**
| id | username  | password | nickname   | email                   |
+----+-----------+----------+------------+-------------------------+
|  1 | admin     | 123      | 炒鸡管理员 | admin@tedu.cn           |
	 */
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String password2;
	

	public void check() throws MsgException{
		

		//3、验证参数是否合法
		if(WebUtils.check(username)){
		//不合法，给予提示，并跳转到regist.jsp
		throw new MsgException("请输入用户名！");
		}
		if(WebUtils.check(password)){
		throw new MsgException("密码不能为空!");
		}
		if(WebUtils.check(password2)){
		throw new MsgException("确认密码不能为空！");
		}
		if(!password.equals(password2)){
		throw new MsgException("两次密码不相同！");
		}
		if(WebUtils.check(nickname)){
		throw new MsgException("昵称不能为空！");
		}
		if(WebUtils.check(email)){
		throw new MsgException("邮箱不能为空!");
		}
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
		throw new MsgException("邮箱格式不正确！");
		}
		}

		
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
