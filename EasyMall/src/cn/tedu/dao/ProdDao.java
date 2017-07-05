package cn.tedu.dao;

import java.util.List;

import cn.tedu.entity.Product;

public interface ProdDao {
	
	public List<Product> findAll();

	/**
	 * ������Ʒid ɾ�����ݿ�����Ʒ
	 * @param id
	 * @return
	 */
	public int deleteProdById(String id);
	
	
	/**
	 * �޸���Ʒ���
	 * @param id	��Ʒid
	 * @param pnum	�޸ĺ���
	 * @return	int ����Ӱ������
	 */
	public int changePnum(String id, int pnum);
	
	/**
	 * ���ݹؼ��ʲ�ѯ������������Ʒ����
	 * @param name ��Ʒ���ƹؼ��֣�ģ����ѯ��
	 * @param cate ��Ʒ����ؼ��֣�ģ����ѯ��
	 * @param min �۸��������Сֵ
	 * @param max �۸���������ֵ
	 * @return ������������Ʒ����
	 */
	public List<Product> findAllByKey(String name, String cate, Double min,
			Double max);

}
