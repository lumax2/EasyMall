package cn.tedu.entity;

import cn.tedu.exception.MsgException;
import utils.WebUtils;

public class User {
	/**
| id | username  | password | nickname   | email                   |
+----+-----------+----------+------------+-------------------------+
|  1 | admin     | 123      | ��������Ա | admin@tedu.cn           |
	 */
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String password2;
	

	public void check() throws MsgException{
		

		//3����֤�����Ƿ�Ϸ�
		if(WebUtils.check(username)){
		//���Ϸ���������ʾ������ת��regist.jsp
		throw new MsgException("�������û�����");
		}
		if(WebUtils.check(password)){
		throw new MsgException("���벻��Ϊ��!");
		}
		if(WebUtils.check(password2)){
		throw new MsgException("ȷ�����벻��Ϊ�գ�");
		}
		if(!password.equals(password2)){
		throw new MsgException("�������벻��ͬ��");
		}
		if(WebUtils.check(nickname)){
		throw new MsgException("�ǳƲ���Ϊ�գ�");
		}
		if(WebUtils.check(email)){
		throw new MsgException("���䲻��Ϊ��!");
		}
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
		throw new MsgException("�����ʽ����ȷ��");
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
