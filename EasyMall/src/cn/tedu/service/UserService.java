package cn.tedu.service;

import cn.tedu.entity.User;
import cn.tedu.exception.MsgException;

public interface UserService {

	public User loginService(String username,String password);
	/**
	 * �ж��û����Ƿ����
	 * @param username
	 * @return true:�û�����  false;�û�������
	 */
	public boolean isExist(String username);
	
	/**ע���û��ķ���
	* @param user����װ���û���Ϣ��User��Ķ���
	* �׳���Exception�쳣������MsgExceptionҲ���У�Ϊʲô��
	*/
	public void regist(User user) throws Exception;
	
	
	/**��¼����
	* @param username:�û���
	* @param pwd������
	* @return �ɹ�����User����ʧ�ܷ���null
	*/
	public User login(String username, String pwd);
	
}