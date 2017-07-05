package cn.tedu.dao;

import java.util.List;

import cn.tedu.entity.Product;

public interface ProdDao {
	
	public List<Product> findAll();

	/**
	 * 根据商品id 删除数据库中商品
	 * @param id
	 * @return
	 */
	public int deleteProdById(String id);
	
	
	/**
	 * 修改商品库存
	 * @param id	商品id
	 * @param pnum	修改后库存
	 * @return	int 返回影响行数
	 */
	public int changePnum(String id, int pnum);
	
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

}
