package cn.tedu.service;

import java.util.List;

import cn.tedu.entity.Product;

public interface ProdService {
	
	
	/**
	 * 查询全部的商品
	 * @return 全部商品的集合对象
	 */
	public List<Product> findAll();
	/**
	 * 根据商品id删除产品
	 * @param id 商品id
	 * @return TRUE：删除成功
	 * 			false ：删除失败
	 */
	public boolean deleteProdById(String id);
	
	/**
	 * 修改库存的方法
	 * @param id 商品id
	 * @param pnum 修改后商品库存
	 * @return true ：修改成功 
	 * 		false：修改失败
	 */
	public boolean changePnum(String id, int pnum);
	/**
	 * 根据关键词查询符合条件的商品集合
	 * @param name 商品名称关键字（模糊查询）
	 * @param cate 商品分类关键字（模糊查询）
	 * @param min 价格区间的最小值
	 * @param max 价格区间的最大值
	 * @return 符合条件的商品集合
	 */
	public List<Product> findAllByKey(String name, String cate, Double min,
			Double max);
	
	/**
	 * 根据商品id查询商品的详细信息
	 * @param id
	 * @return
	 */
	public Product findProdById(String id);
	
	

	
}
