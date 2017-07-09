package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Product;

public interface ProdService {
	
	
	/**
	 * ��ѯȫ������Ʒ
	 * @return ȫ����Ʒ�ļ��϶���
	 */
	public List<Product> findAll();
	/**
	 * ������Ʒidɾ����Ʒ
	 * @param id ��Ʒid
	 * @return TRUE��ɾ���ɹ�
	 * 			false ��ɾ��ʧ��
	 */
	public boolean deleteProdById(String id);
	
	/**
	 * �޸Ŀ��ķ���
	 * @param id ��Ʒid
	 * @param pnum �޸ĺ���Ʒ���
	 * @return true ���޸ĳɹ� 
	 * 		false���޸�ʧ��
	 */
	public boolean changePnum(String id, int pnum);
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
	
	/**
	 * ������Ʒid��ѯ��Ʒ����ϸ��Ϣ
	 * @param id
	 * @return
	 */
	public Product findProdById(String id);
	
	

	
}
