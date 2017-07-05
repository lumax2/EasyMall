package cn.tedu.dao;

import cn.tedu.entity.User;

public interface UserDao {
	

	/**�����û����������ѯ��Ӧ���û�
	* @param username:�û���
	* @param pwd������
	* @return �����򷵻ط�װ��ϢUser����󣬷�֮����null
	*/
	public User findByUnamePwd(String username, String pwd);
	
	
	/**
	 * ע���û�
	 * @param user ��װ���û���Ϣuser�����
	 * @returnӰ������
	 */
	public int regist(User user);
	
	
	/**
	 * �����û��������û�
	 * @param username
	 * @return
	 */
	public User findByUserName(String username);

	/**
	 * ��users�������һ���û���Ϣ
	 * @param user����װ����Ϣ user����
	 * return Ӱ������
	 */
	public int addUser(User user);
	
	
	
}
