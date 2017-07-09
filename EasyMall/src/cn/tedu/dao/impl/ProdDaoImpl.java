package cn.tedu.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.BeanHandler;
import utils.BeanListHandler;
import utils.JDBCUtils;

import cn.tedu.dao.ProdDao;
import cn.tedu.entity.Product;

public class ProdDaoImpl implements ProdDao {

	public List<Product> findAll() {

		String sql = "select * from products";

		try {
			return JDBCUtils.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Product>();
	}

	public int deleteProdById(String id) {

		String sql = "delete from products where id=?";
		try {
			return JDBCUtils.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int changePnum(String id, int pnum) {

		String sql = "update  products  set pnum =? where id =?";
		try {
			return JDBCUtils.update(sql, pnum, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<Product> findAllByKey(String name, String cate, Double min,
			Double max) {
		String sql = "select * from products where name like ? and category like ?  ";

		// ¶¯Ì¬Æ´½ÓsqlÓï¾ä
		try {
			if (min != null && max != null) {
				sql +=  "and price >= ? and price <=?";
				return JDBCUtils.query(sql, new BeanListHandler<Product>(
						Product.class), "%"+name+ "%", "%"+cate+"%", min ,max);
			} else if (min != null && max == null) {
				sql +=  "and price >= ? ";
				return JDBCUtils.query(sql, new BeanListHandler<Product>(
						Product.class), "%" +name+ "%", "%" + cate + "%", min );
			} else if (min == null && max != null) {
				sql += "and price <= ? ";
				return JDBCUtils.query(sql, new BeanListHandler<Product>(
						Product.class), "%" +name+ "%", "%" +cate+ "%", max);
			} else  {
				return JDBCUtils.query(sql, new BeanListHandler<Product>(
						Product.class),"%" +name+"%", "%" +cate+ "%");
			}

		} catch (Exception e) {
			return new ArrayList<Product>();
		}

		
	}

	public Product findProdById(String id) {
		
		String sql = "select * from products where id =?";
		
		try {
			return JDBCUtils.query(sql, new BeanHandler<Product>(Product.class), id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
